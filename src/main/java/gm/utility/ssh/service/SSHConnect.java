package gm.utility.ssh.service;

import gm.utility.ssh.domain.SSHModel;

public interface SSHConnect {
    void connect(SSHModel model);
    void execute(String command,String type);
    String getResponse();
    void disconnect();
}
