package learn.wireless_guardian.data;

import learn.wireless_guardian.models.SalesRep;
import learn.wireless_guardian.models.Site;

import java.util.List;

public interface SalesRepRepository {

    List<SalesRep> findAll();

    SalesRep findById(int salesRep);

    SalesRep add (SalesRep salesRep);

    boolean update(SalesRep salesRep);

    boolean deleteById(int salesRepId);
}
