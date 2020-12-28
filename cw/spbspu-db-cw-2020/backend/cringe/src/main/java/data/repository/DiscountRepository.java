package data.repository;

import data.dao.Dao;
import data.dao.DiscountDao;
import data.dao.TourDao;
import data.model.Discount;
import data.model.Tour;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DiscountRepository {
  private static DiscountRepository instance;
  private final DiscountDao discountDao;
  private final TourDao tourDao;

  private DiscountRepository(
      DiscountDao discountDao,
      TourDao tourDao
  ) {
    this.discountDao = discountDao;
    this.tourDao = tourDao;
  }

  public static synchronized DiscountRepository getInstance(
      DiscountDao discountDao,
      TourDao tourDao
  ) {
    if (instance == null) {
      instance = new DiscountRepository(discountDao, tourDao);
    }
    return instance;
  }

  public Discount saveDiscount(Discount discount) throws SQLException {
    Tour tour = tourDao.selectById(discount.getTourId());
    if (tour == null) {
      throw new SQLException("Tour for this discount was not found");
    }
    List<Discount> existingDiscounts = discountDao.selectAllDiscountsByTourId(
        discount.getTourId()
    );
    int allDiscountsSum = getDiscountSum(existingDiscounts) + discount.getDiscountAmount();
    if (tour.getPrice() > allDiscountsSum)
      return discountDao.create(discount);
    else {
      throw new SQLException("Sum of discounts cannot be more than price");
    }
  }

  public List<Discount> findAllDiscountsByTourId(int tourId) throws SQLException {
    throwAnExceptionIfTourNotExists(tourId);
    return discountDao.selectAllDiscountsByTourId(tourId);
  }

  private int getDiscountSum(List<Discount> existingDiscounts) {
    Optional<Integer> optionalSum =
        existingDiscounts.stream().map(Discount::getDiscountAmount).reduce(Integer::sum);
    return optionalSum.orElse(0);
  }

  public Discount updateDiscount(int id, Discount discount) throws SQLException {
    Tour tour = tourDao.selectById(discount.getTourId());
    if (tour == null) {
      throw new SQLException("Tour for this discount was not found");
    }
    List<Discount> existingDiscounts = discountDao.selectAllDiscountsByTourId(
        discount.getTourId()
    );
    int allDiscountsSum = getDiscountSum(existingDiscounts) + discount.getDiscountAmount();
    if (tour.getPrice() > allDiscountsSum)
      return discountDao.updateById(id, discount);
    else {
      throw new SQLException("Sum of discounts cannot be more than price");
    }
  }

  public Discount findDiscountById(int id) throws SQLException {
    return discountDao.selectById(id);
  }

  public List<Discount> findAllDiscounts() throws SQLException {
    return discountDao.selectAll();
  }

  public void deleteDiscount(int id) throws SQLException {
    discountDao.delete(id);
  }

  public void deleteAllDiscountsByTourId(int tourId) throws SQLException {
    throwAnExceptionIfTourNotExists(tourId);
    discountDao.deleteAllDiscountsByTourId(tourId);
  }

  private void throwAnExceptionIfTourNotExists(int tourId) throws SQLException {
    Tour tour = tourDao.selectById(tourId);
    if (tour == null) {
      throw new SQLException("Tour for this discount was not found");
    }
  }
}
