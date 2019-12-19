#include "mainwindow.h"
#include "ui_mainwindow.h"
#include <QDebug>

static const int MATRIX_SIZE = 25;

MainWindow::MainWindow(QWidget *parent)
  : QMainWindow(parent)
  , ui(new Ui::MainWindow)
{
  ui->setupUi(this);

  ui->trainSlider->setTickInterval(50);
  ui->trainSlider->setTickPosition(QSlider::TicksAbove);
  connect(ui->trainSlider, SIGNAL(valueChanged(int)),
          this, SLOT(onTrainSliderChanged()));

  ui->rateSlider->setTickInterval(50);
  ui->rateSlider->setTickPosition(QSlider::TicksAbove);
  connect(ui->rateSlider, SIGNAL(valueChanged(int)),
          this, SLOT(onRateSliderChanged()));

  group = new QButtonGroup(this);
  group->addButton(ui->pix_1, 1);
  group->addButton(ui->pix_2, 2);
  group->addButton(ui->pix_3, 3);
  group->addButton(ui->pix_4, 4);
  group->addButton(ui->pix_5, 5);
  group->addButton(ui->pix_6, 6);
  group->addButton(ui->pix_7, 7);
  group->addButton(ui->pix_8, 8);
  group->addButton(ui->pix_9, 9);
  group->addButton(ui->pix_10, 10);
  group->addButton(ui->pix_11, 11);
  group->addButton(ui->pix_12, 12);
  group->addButton(ui->pix_13, 13);
  group->addButton(ui->pix_14, 14);
  group->addButton(ui->pix_15, 15);
  group->addButton(ui->pix_16, 16);
  group->addButton(ui->pix_17, 17);
  group->addButton(ui->pix_18, 18);
  group->addButton(ui->pix_19, 19);
  group->addButton(ui->pix_20, 20);
  group->addButton(ui->pix_21, 21);
  group->addButton(ui->pix_22, 22);
  group->addButton(ui->pix_23, 23);
  group->addButton(ui->pix_24, 24);
  group->addButton(ui->pix_25, 25);
  connect(group, SIGNAL(buttonClicked(int)),
          this, SLOT(onGroupButtonClicked(int)));
}


MainWindow::~MainWindow()
{
  delete ui;
}

void MainWindow::onTrainSliderChanged()
{
  QString trainValue = QString::number((ui->trainSlider->value() + 1) * 100);
  ui->trainNumber->setText(trainValue);
}

void MainWindow::onRateSliderChanged()
{
  double valueAsDouble = ((ui->rateSlider->value() + 1.0) / 100.0);
  if (valueAsDouble < 1.0 && valueAsDouble > 0) {
    QString rateValue = QString::number(valueAsDouble);
    ui->rateNumber->setText(rateValue);
  }
}

void MainWindow::onGroupButtonClicked(int id)
{
    if (group->button(id)->isChecked() == false) {
      group->button(id)->setStyleSheet("background-color: rgb(255, 84, 152)");
      group->button(id)->setCheckable(true);
    }
    else {
      group->button(id)->setCheckable(false);
      group->button(id)->setStyleSheet("background-color: rgb(56, 65, 76)");
    }
}

std::vector<int> MainWindow::getLinearMatrix()
{
  std::vector<int> outputLinearMatrix;
  for (int i = 1; i <= MATRIX_SIZE; i++)
  {
    if (group->button(i)->isCheckable())
      outputLinearMatrix.push_back(1);
    else
      outputLinearMatrix.push_back(0);
  }
  return outputLinearMatrix;
}

void MainWindow::on_submit_clicked()
{
  std::vector<int> linearMatrix = getLinearMatrix();
  trainTimes = ui->trainNumber->text().toInt();
  rateNumber = ui->rateNumber->text().toDouble();
  writeToFile(linearMatrix);
  Api *api = new Api();
  setupApi(api);
  api->trainNeuralNetwork(trainTimes);
  api->setLearningRate(rateNumber);
  int *result = api->predict();
  ui->digit->setText(QString::number(result[0]));
}

void MainWindow::setupApi(Api *api)
{
  const int INPUTS = 25; //number of inputs nodes
  const int HIDDEN = 64; //number of hidden nodes
  const int OUTPUTS = 10; //number of outputs nodes
  const int TOTAL_TRAINING_AMOUNT = 10; //number of training examples
  const int TOTAL_TESTING_AMOUNT = 1; //number of testing examples
  api->setup(INPUTS, HIDDEN, OUTPUTS, TOTAL_TRAINING_AMOUNT, TOTAL_TESTING_AMOUNT);
}

void MainWindow::writeToFile(std::vector<int> linearMatrix)
{
  std::ofstream outfile ("../../../res/output.txt");
  if (outfile.is_open())
  {
    outfile << "@\n";
    for (int i = 0; i < MATRIX_SIZE; i++)
    {
      if ((i % 5 == 0) && (i != 0))
        outfile << '\n';
      outfile << linearMatrix[i];
    }
    outfile.close();
  }
}
