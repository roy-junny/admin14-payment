package bookrental;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static java.lang.Thread.sleep;

@Entity
@Table(name="Payment_table")
public class Payment {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long rentalId;
    private Long bookId;
    private Integer rate;
    private Date regDate;
    private String payStatus;

    @PostPersist
    public void onPostPersist(){

        System.out.println("Payment-->onPostPersist() ");

        if("REQ_PAY".equals(this.getPayStatus() ) ) {
            Paid paid = new Paid();
            BeanUtils.copyProperties(this, paid);
            paid.publishAfterCommit();
        }
        else {
            System.out.println("onPostPersist(), payStatus is " + this.getPayStatus());
        }

    }

    @PostUpdate
    public void onPostUpdate() {

        System.out.println("Payment-->onPostUpdate() ");

        if("CANCELLED".equals(this.getPayStatus() ) ) {
            Refunded refunded = new Refunded();
            BeanUtils.copyProperties(this, refunded);
            refunded.publishAfterCommit();
        }
        else {
            System.out.println("onPostUpdate(), payStatus is " + this.getPayStatus());
        }

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getRentalId() {
        return rentalId;
    }

    public void setRentalId(Long rentalId) {
        this.rentalId = rentalId;
    }
    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }
    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        Date today = new Date();
        this.regDate = today;

    }

    public Long getBookId() {
        return bookId;
    }
    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getPayStatus() {
        return payStatus;
    }
    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

}
