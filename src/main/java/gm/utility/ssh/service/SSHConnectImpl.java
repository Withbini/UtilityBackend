package gm.utility.ssh.service;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import gm.utility.ssh.domain.SSHModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
@Slf4j
public class SSHConnectImpl implements SSHConnect {
    private Session session;
    private ChannelExec channelExec;

    @Override
    public void connect(SSHModel model) {
        if (model == null)
            throw new RuntimeException("ssh model does not exist.");
        try {
            session = new JSch().getSession(model.username(), model.ip(), model.port());
            session.setPassword(model.password());
            session.setConfig("StrictHostKeyChecking", "no");
            session.setConfig("UserKnownHostsFile", "/dev/null");
            session.connect();
        } catch (JSchException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void execute(String command, String type) {
        try {
            channelExec = (ChannelExec) session.openChannel(type);
            channelExec.setCommand(command);
            channelExec.connect();
        } catch (JSchException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getResponse() {
        StringBuilder response = new StringBuilder();
        try {
            InputStream inputStream = channelExec.getInputStream();
            byte[] buffer = new byte[8192];
            int decodedLength;
            while ((decodedLength = inputStream.read(buffer, 0, buffer.length)) > 0)
                response.append(new String(buffer, 0, decodedLength));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            this.disconnect();
        }
        return response.toString();
    }

    @Override
    public void disconnect() {
        if (session != null) session.disconnect();
        if (channelExec != null) channelExec.disconnect();
    }
}
