#ifndef MATRIX_HPP
#define MATRIX_HPP

class Matrix
{
    public:
        Matrix(const int, const int);
        ~Matrix();
        void setData(int, int, double);
        double ** getData();
        int getRows();
        int getCols();
        void randomize();
        void multiply(double); //scalar product
        void multiply(Matrix *); //hadamard product
        static Matrix * multiply(Matrix *, Matrix *); //matrix product
        void add(double);
        void add(Matrix *);
        static Matrix * substract(Matrix *, Matrix *);
        static Matrix * add(Matrix *, Matrix *);
        static Matrix * map(Matrix *, double(*)(double));
        double * toArray();
        static Matrix * transpose(Matrix *);
        static Matrix * fromArray(double[], int);
        void map(double(*)(double));
        void print();
    private:
        int rows;
        int cols;
        double **data;
};

#endif // MATRIX_HPP