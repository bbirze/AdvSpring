package com.example.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.domain.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	private class CustomerRowMapper implements RowMapper<Customer> {

		public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
			Customer cust = new Customer();
			cust.setCustomerID(rs.getInt("CustomerID"));
			cust.setEmail(rs.getString("Email"));
			cust.setName(rs.getString("Name"));
			cust.setPhone(rs.getString("Phone"));
			return cust;
		}
	}

	private JdbcTemplate template;
	private CustomerRowMapper mapper;
	private DataSource customerDataSource;

	@Autowired
	public void setDataSource(DataSource ds) {
		customerDataSource = ds;
		template = new JdbcTemplate(ds);
		mapper = new CustomerRowMapper();
	}

	@Override
	public List<Customer> allCustomers() {
		List<Customer> customers = template.query("SELECT * FROM Customer", mapper);
		return customers;
	}

}
