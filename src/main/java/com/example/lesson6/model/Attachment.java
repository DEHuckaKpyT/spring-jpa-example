package com.example.lesson6.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;
import java.util.UUID;

/**
 * Created on 20.11.2023.
 * <p>
 *
 * @author Denis Matytsin
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Attachment {

    @Column(nullable = false)
    UUID nodeId;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String format;

    @Column(nullable = false)
    Long size;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attachment attachment = (Attachment) o;
        return Objects.equals(nodeId, attachment.nodeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeId);
    }
}
