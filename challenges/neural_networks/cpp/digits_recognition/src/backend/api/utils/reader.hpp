#ifndef READER_HPP
#define READER_HPP

class Reader
{
    public:
      static Data ** readTrainData(const int, const int, const int);
      static Data ** readTestData(const int, const int, const int);
};

#endif // READER_HPP