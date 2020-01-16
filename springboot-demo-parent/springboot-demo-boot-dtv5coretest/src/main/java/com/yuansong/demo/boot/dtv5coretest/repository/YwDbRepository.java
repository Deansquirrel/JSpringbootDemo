package com.yuansong.demo.boot.dtv5coretest.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yuansong.demo.boot.dtv5coretest.repository.dto.JgInfo;
import com.yuansong.demo.boot.dtv5coretest.repository.dto.rowMapper.JgInfoRowMapper;

@Repository
public class YwDbRepository {

//	private static final Logger logger = LoggerFactory.getLogger(YwDbRepository.class);
	
	@Autowired
	@Qualifier("jdbcTemplateDefault")
	private JdbcTemplate jdbcTemplate;
		
	public Connection getConnection() throws SQLException {
		return this.jdbcTemplate.getDataSource().getConnection();
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return this.jdbcTemplate;
	}
	
	/**
	 * 查询本机构ID
	 * 
	 * @return
	 */
	@Transactional(value = "txManagerDefault", rollbackFor = Exception.class)
	public int GetJgId() throws DataAccessException {
		return this.jdbcTemplate.queryForObject("select coid from zlcompany", Integer.class);
	}
	
	/**
	 * 查询机构列表
	 * 
	 * @return
	 */
	@Transactional(value = "txManagerDefault", rollbackFor = Exception.class)
	public List<JgInfo> GetJgList_gs() throws DataAccessException {
		return this.getJgList("gs", "", true);
	}
	
	@Transactional(value = "txManagerDefault", rollbackFor = Exception.class)
	public List<JgInfo> GetJgList_wlzx(boolean sjyxx) throws DataAccessException {
		return this.getJgList("wlzx", "wlzx", sjyxx);
	}
	
	@Transactional(value = "txManagerDefault", rollbackFor = Exception.class)
	public List<JgInfo> GetJgList_md(boolean sjyxx) throws DataAccessException {
		return this.getJgList("md", "md", sjyxx);
	}
	
	@Transactional(value = "txManagerDefault", rollbackFor = Exception.class)
	public List<JgInfo> GetJgListWithoutB_wlzx(boolean sjyxx) throws DataAccessException {
		return this.getJgList("wlzx+isnotbfzjg", String.valueOf(this.GetJgId()), sjyxx);
	}
	
	@Transactional(value = "txManagerDefault", rollbackFor = Exception.class)
	public List<JgInfo> GetJgListWithoutB_md(boolean sjyxx) throws DataAccessException {
		return this.getJgList("md+isnotbfzjg", String.valueOf(this.GetJgId()), sjyxx);
	}
	
	@Transactional(value = "txManagerDefault", rollbackFor = Exception.class)
	public List<JgInfo> GetJgList_all(boolean sjyxx) throws DataAccessException {
		return this.getJgList("", "", sjyxx);
	}
	
	/**
	 * [接口] 公共功能 机构列表信息
	 * 
	 * @param funcParas
	 * @param parasValue
	 * @param sjyxx 是否有效
	 * @return
	 * @throws DataAccessException
	 */
	@Transactional(value = "txManagerDefault", rollbackFor = Exception.class)
	private List<JgInfo> getJgList(String funcParas, String parasValue, boolean sjyxx) throws DataAccessException {
		this.jdbcTemplate.update(""
				+ "CREATE TABLE [#Commonjglist]("
				+ "	[fzjgid] int primary key,"
				+ "	[fzjgxsmc] nvarchar(20),"
				+ "	[fzjglx] int, [fzjgpym] varchar(20),"
				+ "	[fzjgsym] varchar(6), "
				+ "	[fzjgtdm] char(3), "
				+ "	[fzjgxsm] varchar(10), "
				+ "	[fzjgisforbidden] tinyint "
				+ ")");
		this.jdbcTemplate.update("exec pr_Commonjglist_fill ?, ?, ?", funcParas, parasValue, sjyxx ? 1 : 0);
		List<JgInfo> list = this.jdbcTemplate.query(""
				+ "select [fzjgid],[fzjgxsmc] ,[fzjglx] ,[fzjgpym] ,[fzjgsym] ,[fzjgtdm] ,[fzjgxsm] ,[fzjgisforbidden] "
				+ "from [#Commonjglist]", 
				new JgInfoRowMapper());
		this.jdbcTemplate.update("DROP TABLE [#Commonjglist]");
		return list;
	}
	
}
