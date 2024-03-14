package gm.utility.ssh.dto;

public record SSHReqDto(String username, String ip, int port, String command, String password) {
}
