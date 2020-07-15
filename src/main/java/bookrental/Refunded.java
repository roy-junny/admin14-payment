
package bookrental;

import java.util.Date;

public class Refunded extends AbstractEvent {

    private Long id;
    private Long rentalId;
    private Integer rate;
    private Date regDate;


    public Refunded()
    {
        super();
    }
    public  Refunded(Payment payment)
    {
        this();
        setId(payment.getId());
        setRentalId(payment.getRentalId());
        setRate(payment.getRate());
        setRegTime(payment.getRegDate());
        System.out.println("Success");

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
    public Date getRegTime() {
        return regDate;
    }

    public void setRegTime(Date regDate) {
        this.regDate = regDate;
    }
}
