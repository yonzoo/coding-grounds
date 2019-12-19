#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QButtonGroup>
#include <QVector>
#include <fstream>
#include <iostream>
#include "../../backend/api/api.hpp"

QT_BEGIN_NAMESPACE
namespace Ui { class MainWindow; }
QT_END_NAMESPACE

class MainWindow : public QMainWindow
{
  Q_OBJECT

public:
  MainWindow(QWidget *parent = nullptr);
  ~MainWindow();

private slots:
  void onGroupButtonClicked(int id);
  void onTrainSliderChanged();
  void onRateSliderChanged();
  void on_submit_clicked();

private:
  Ui::MainWindow *ui;
  QButtonGroup* group;
  std::vector<int> getLinearMatrix();
  void setupApi(Api *api);
  void writeToFile(std::vector<int> kek);
  int trainTimes;
  double rateNumber;
};
#endif // MAINWINDOW_H
