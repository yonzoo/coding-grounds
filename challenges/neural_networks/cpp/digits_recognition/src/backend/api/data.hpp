#ifndef DATA_HPP
#define DATA_HPP

class Data
{
    public:
      Data(const int INPUTS, const int OUTPUTS);
      ~Data();
      double * getData();
      double * getTargets();
      void setTrainData(const int i, const double value);
      void setTrainTargets(const int i, const double value);
    private:
        double *data;
        double *targets;
};

#endif // DATA_HPP