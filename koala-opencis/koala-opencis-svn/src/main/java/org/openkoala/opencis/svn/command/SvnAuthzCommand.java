package org.openkoala.opencis.svn.command;

import org.openkoala.opencis.api.OpencisConstant;
import org.openkoala.opencis.api.Project;

import com.dayatang.configuration.Configuration;
import com.trilead.ssh2.Connection;
import com.trilead.ssh2.Session;

/**
 * svn分配用户到某个角色
 */
public class SvnAuthzCommand extends SvnCommand {

	private String role;
	
	public SvnAuthzCommand() {
		
	}
	
	public SvnAuthzCommand(String role, Configuration configuration,Project project){
		super(configuration, project);
		this.role = role;
	}
	
	@Override
	public String getCommand() {
		String groupName = project.getProjectName() + "_" + role;
		StringBuilder authzCommand = new StringBuilder();
		authzCommand.append("grep -q '\\[").append(project.getProjectName()).append(":/\\]' ")
					.append(OpencisConstant.PROJECT_PATH_IN_LINUX_SVN).append(project.getProjectName()).append("/conf/authz ")
					.append("&& sed -i '/\\[").append(project.getProjectName()).append(":\\/\\]/a ")
					.append("@").append(groupName).append("=rw' ")
					.append(OpencisConstant.PROJECT_PATH_IN_LINUX_SVN).append(project.getProjectName()).append("/conf/authz ")
					.append("|| echo -ne '\n[").append(project.getProjectName()).append(":/]\n@")
					.append(groupName).append("=rw\n' >> ")
					.append(OpencisConstant.PROJECT_PATH_IN_LINUX_SVN).append(project.getProjectName()).append("/conf/authz");
		return authzCommand.toString();
	}

	@Override
	public void doWork(Connection connection, Session session) {
		
	}

}