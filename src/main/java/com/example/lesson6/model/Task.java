package com.example.lesson6.model;

import com.example.lesson6.util.CollectionUtil;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static javax.persistence.FetchType.EAGER;

/**
 * Created on 20.11.2023.
 * <p>
 * Задача
 *
 * @author Denis Matytsin
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Task {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    private String description;

    /** Теги задачи */
    @Column(name = "tag")
    @ElementCollection(fetch = EAGER)
    private Set<String> tags;

    /** Прикреплённые файлы */
    @OrderColumn(name = "attachment_number", nullable = false)
    @ElementCollection(fetch = EAGER)
    private List<Attachment> attachments;

    /** Метод для оптимизации вставки тегов */
    public void setTags(Set<String> tags) {
        this.tags = CollectionUtil.replaceContents(this.tags, tags);
    }

    /** Метод для оптимизации вставки файлов */
    public void setAttachments(List<Attachment> attachments) {
        this.attachments = CollectionUtil.replaceContents(this.attachments, attachments);
    }
}
