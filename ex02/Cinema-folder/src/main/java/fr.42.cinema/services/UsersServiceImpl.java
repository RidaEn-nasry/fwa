
package fr.fortytwo.cinema.services;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import fr.fortytwo.cinema.repositories.AuthLogsRepository;
import fr.fortytwo.cinema.repositories.FileMappingRepository;
import fr.fortytwo.cinema.repositories.UsersRepository;
import fr.fortytwo.cinema.services.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import fr.fortytwo.cinema.models.AuthLogs;
import fr.fortytwo.cinema.models.FileMapping;
import fr.fortytwo.cinema.models.User;
import jakarta.servlet.ServletException;

@Service("usersServiceImpl")
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository UsersRepository;

    @Autowired
    private FileMappingRepository fileMappingRepository;

    @Autowired
    private AuthLogsRepository authLogsRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User signUp(String firstName, String lastName, String password, String phoneNumber, String email) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(passwordEncoder.encode(password));
        user.setPhoneNumber(phoneNumber);
        user.setEmail(email);
        return UsersRepository.save(user);
    }

    @Override
    public User signIn(String email, String password) {
        User user = UsersRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Wrong password");
        }
        return user;

    }

    private void writeFileToDisk(Part filePart, String storagePath, String fileName) throws IOException {
        filePart.write(storagePath + fileName);
    }

    @Override
    public FileMapping updateProfilePicture(Part filePart, Long userId, String storagePath)
            throws IOException, ServletException {
        FileMapping fileMapping = new FileMapping();
        fileMapping.setOriginalFileName(filePart.getSubmittedFileName());
        String ext = fileMapping.getOriginalFileName().substring(fileMapping.getOriginalFileName().lastIndexOf("."));
        String hashedFileName = fileMapping.getOriginalFileName().lastIndexOf('.') + "_" + userId + "_"
                + System.currentTimeMillis()
                + ext;
        fileMapping.setGeneratedFileName(hashedFileName);
        fileMapping.setMimeType(filePart.getContentType());
        fileMapping.setSize(filePart.getSize());
        fileMapping.setPath(storagePath);
        fileMapping.setUserId(userId);
        writeFileToDisk(filePart, storagePath, hashedFileName);
        fileMappingRepository.save(fileMapping);
        return fileMapping;
    }

    @Override

    public List<FileMapping> getProfilePictures(Long userId) {
        return UsersRepository.findByUserId(userId);
    }

    @Override
    public FileMapping getProfilePictureByName(String originalFileName) {
        return fileMappingRepository.findByOriginalFileName(originalFileName);
    }

    @Override
    public AuthLogs addAuthLog(AuthLogs authLog) {
        return authLogsRepository.save(authLog);
    }

    @Override
    public List<AuthLogs> getAuthLogs(Long userId) {
        return authLogsRepository.findByUserId(userId);
    }

    @Override
    public void updateUsersTimeSpent(Long userId, Timestamp attemptedAt, String ipAddress, Integer timeSpent) {
        authLogsRepository.updateTimeSpent(userId, attemptedAt, ipAddress, timeSpent);
    }

}