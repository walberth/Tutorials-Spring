package com.pluralsight.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.pluralsight.model.Ride;
import com.pluralsight.repository.util.RideRowMapper;

@Repository("rideRepository")
public class RideRepositoryImpl implements RideRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Ride createRide(Ride ride) {		
		//jdbcTemplate.update("insert into ride (name, duration) values (?,?)", ride.getName(), ride.getDuration());
			
//		KeyHolder keyHolder = new GeneratedKeyHolder();
//		jdbcTemplate.update(new PreparedStatementCreator() {
//			
//			@Override
//			public PreparedStatement createPreparedStatement(Connection con)
//					throws SQLException {
//				
//				PreparedStatement ps = con.prepareStatement("insert into ride (name, duration) values (?,?)", new String [] {"id"});
//				ps.setString(1, ride.getName());
//				ps.setInt(2, ride.getDuration());
//				return ps;
//			}
//		}, keyHolder);
//		
//		Number id = keyHolder.getKey();
		
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
		
		insert.setGeneratedKeyName("id");
		
		Map<String, Object> data = new HashMap<>();
		data.put("name", ride.getName());
		data.put("duration", ride.getDuration());
		
		List<String> columns = new ArrayList<>();
		columns.add("name");
		columns.add("duration");
		
		insert.setTableName("ride");
		insert.setColumnNames(columns);
		Number id = insert.executeAndReturnKey(data);
		
		return getRide(id.intValue());
	}
	
	public Ride getRide(Integer id) {
		Ride ride = jdbcTemplate.queryForObject("select * from ride where id = ?", new RideRowMapper(), id);
		
		return ride;
	}
	
	@Override
	public List<Ride> getRides() {
		
		List<Ride> rides = jdbcTemplate.query("select * from ride", new RideRowMapper());
		
		return rides;
	}
	
}
