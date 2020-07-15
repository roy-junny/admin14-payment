package bookrental;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaymentRepository extends PagingAndSortingRepository<Payment, Long>{

    List<Payment> findByRentalIdAndBookId(@Param("rentalId") Long rentalId, @Param("bookId") Long bookId );
}