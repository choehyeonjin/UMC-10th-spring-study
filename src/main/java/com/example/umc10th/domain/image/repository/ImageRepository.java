package com.example.umc10th.domain.image.repository;

import com.example.umc10th.domain.image.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}