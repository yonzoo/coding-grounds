#include <iostream>
#include <stdlib.h>
#include <time.h> 
#include "matrix.hpp"

Matrix::Matrix(const int rows, const int cols) {
  this->rows = rows;
  this->cols = cols;
  data = new double*[rows];
  for (int i = 0; i < rows; ++i) {
    data[i] = new double[cols];
    for (int j = 0; j < cols; j++) {
      data[i][j] = 0;
    }
  }
}

Matrix::~Matrix() {
  for (int i = 0; i < rows; i++) {
    delete data[i];
  }
  delete[] data;
}

double ** Matrix::getData() {
  return data;
}

int Matrix::getRows() {
  return rows;
}

int Matrix::getCols() {
  return cols;
}

void Matrix::setData(int i, int j, double value) {
  if (i < rows && j < cols) {
    data[i][j] = value;
  }
  else {
    std::cout << "Out of bounds";
  }
}

void Matrix::randomize() {
  srand (time(0));
  for (int i = 0; i < rows; i++) {
    for (int j = 0; j < cols; j++) {
      data[i][j] = (rand() / (RAND_MAX / 2.0)) - 1.0;
    }
  }
}

void Matrix::multiply(double d) {
    for (int i = 0; i < rows; i++) {
    for (int j = 0; j < cols; j++) {
      data[i][j] *= d;
    }
  }
}

void Matrix::multiply(Matrix *m) {
    double ** m_data = m->getData();
    for (int i = 0; i < rows; i++) {
    for (int j = 0; j < cols; j++) {
      data[i][j] *= m_data[i][j];
    }
  }
}

Matrix * Matrix::multiply(Matrix *m1, Matrix *m2) {
    if (m1->getCols() == m2->getRows()) {
      Matrix *result = new Matrix(m1->getRows(), m2->getCols());
      for (int i = 0; i < m1->getRows(); i++) {
        for (int j = 0; j < m2->getCols(); j++) {
          double sum = 0;
          for (int k = 0; k < m1->getCols(); k++) {
            sum += m1->getData()[i][k] * m2->getData()[k][j];
          }
          result->setData(i, j, sum);
        }
      }
      return result;
    }
    else {
      std::cout << "Matrix 1 is " << m1->getRows() << " x " << m1->getCols() << "\n";
      std::cout << "Matrix 2 is " << m2->getRows() << " x " << m2->getCols() << "\n";
      std::cout << "Both matrices should have the same number of columns" << "\n";
      return nullptr;
    }
}

void Matrix::add(double d) {
    for (int i = 0; i < rows; i++) {
    for (int j = 0; j < cols; j++) {
      data[i][j] += d;
    }
  }
}

void Matrix::add(Matrix *m) {
    double ** m_data = m->getData();
    for (int i = 0; i < rows; i++) {
    for (int j = 0; j < cols; j++) {
      data[i][j] += m_data[i][j];
    }
  }
}

Matrix * Matrix::add(Matrix *m1, Matrix *m2) {
    if ((m1->getRows() == m2->getRows()) && (m1->getCols() == m2->getCols())) {
      Matrix *result = new Matrix(m1->getRows(), m1->getCols());
      for (int i = 0; i < m1->getRows(); i++) {
        for (int j = 0; j < m1->getCols(); j++) {
            result->setData(i, j, m1->getData()[i][j] + m2->getData()[i][j]);
        }
      }
      return result;
    }
    else {
      std::cout << "Both matrices should have the same number of rows and columns";
      return nullptr;
    }
}

Matrix * Matrix::substract(Matrix *m1, Matrix *m2) {
    if ((m1->getRows() == m2->getRows()) && (m1->getCols() == m2->getCols())) {
      Matrix *result = new Matrix(m1->getRows(), m1->getCols());
      for (int i = 0; i < m1->getRows(); i++) {
        for (int j = 0; j < m1->getCols(); j++) {
            result->setData(i, j, m1->getData()[i][j] - m2->getData()[i][j]);
        }
      }
      return result;
    }
    else {
      std::cout << "Both matrices should have the same number of rows and columns";
      return nullptr;
    }
}

double * Matrix::toArray() {
  double * arr = new double[rows * cols];
  int k = 0;
  for (int i = 0; i < getRows(); i++) {
    for (int j = 0; j < getCols(); j++) {
      arr[k] = data[i][j];
      ++k;
    }
  }
  return arr;
}

Matrix * Matrix::fromArray(double arr[], int size) {
  Matrix *m = new Matrix(size, 1);
  for (int i = 0; i < size; i++) {
    m->setData(i, 0, arr[i]);
  }
  return m;
}

void Matrix::map(double(*func_ptr)(double)) {
  for (int i = 0; i < rows; i++) {
    for (int j = 0; j < cols; j++) {
      data[i][j] = (*func_ptr)(data[i][j]);
    }
  }
}

Matrix * Matrix::map(Matrix *m, double(*func_ptr)(double)) {
    Matrix *result = new Matrix(m->getRows(), m->getCols());
    for (int i = 0; i < result->getRows(); i++) {
    for (int j = 0; j < result->getCols(); j++) {
      result->setData(i, j, (*func_ptr)(m->getData()[i][j]));
    }
  }
  return result;
}

Matrix * Matrix::transpose(Matrix *m) {
    Matrix *result = new Matrix(m->getCols(), m->getRows());
    for (int i = 0; i < m->getRows(); i++) {
      for (int j = 0; j < m->getCols(); j++) {
        result->setData(j, i, m->getData()[i][j]);
      }
    }
    return result;
}

void Matrix::print() {
  for (int i = 0; i < rows; i++) {
    for (int j = 0; j < cols; j++) {
      std::cout << data[i][j] << " ";
    }
    std::cout << '\n';
  }
}

