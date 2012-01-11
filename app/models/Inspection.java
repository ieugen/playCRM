package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Models an periodic car inspection.
 * User: ieugen
 * Date: 11.01.2012
 * Time: 03:33
 */
@Entity
public class Inspection extends Model {
    public String info;
    @Temporal(TemporalType.DATE)
    public Date expires;
    @OneToOne
    public Car car;
}
