package schedule.dao.impl;

import org.hibernate.Query;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Repository;
import schedule.dao.api.TicketDao;
import schedule.entity.ScheduleEntity;
import schedule.entity.TicketEntity;
import schedule.entity.TrainEntity;
import schedule.entity.UserEntity;


@Repository
public class TicketDaoImpl extends GeneralCrudDaoImpl<TicketEntity> implements TicketDao {

    @Override
    public void addTicket(TicketEntity ticketEntity) {
        getCurrentSession().save(ticketEntity);
    }

    @Override
    public boolean findTicket(TicketEntity ticketEntity) {
        LocalDateTime ldt = ticketEntity.getDepartureDateTime();
        ScheduleEntity depSch = ticketEntity.getDepartureSchedule();
        UserEntity user = ticketEntity.getUserEntity();
        TrainEntity train = ticketEntity.getTrainEntity();


        Query queryDate = getCurrentSession().createQuery("FROM TicketEntity te WHERE te.departureDateTime =:ldt AND te.trainEntity =:train AND te.departureSchedule =:depSch AND te.userEntity =:user");
        queryDate.setParameter("ldt", ldt);
        queryDate.setParameter("train", train);
        queryDate.setParameter("depSch", depSch);
        queryDate.setParameter("user", user);
        if (queryDate.list().size() != 0 /*|| queryTrain.list().size() == 0 || querySchedule.list().isEmpty()|| queryUser.uniqueResult() == null*/) {
            return true;
        }
        return false;
    }
}
