package ru.hogwarts.school.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hogwarts.school.model.Avatar;

@Data
@NoArgsConstructor
public class AvatarDTO {

    private Long id;
    private String filePath;
    private long fileSize;
    private String mediaType;
    private byte[] data;
    private Long studentId;

    public static AvatarDTO fromAvatar (Avatar avatar) {
        AvatarDTO dto = new AvatarDTO();
        dto.setId(avatar.getId());
        dto.setFilePath(avatar.getFilePath());
        dto.setFileSize(avatar.getFileSize());
        dto.setMediaType(avatar.getMediaType());
        dto.setData(avatar.getData());
        return dto;
    }

    public Avatar toAvatar() {
        Avatar avatar = new Avatar();
        avatar.setId(this.getId());
        avatar.setFilePath(this.getFilePath());
        avatar.setFileSize(this.getFileSize());
        avatar.setMediaType(this.getMediaType());
        avatar.setData(this.getData());
        return avatar;
    }

}
