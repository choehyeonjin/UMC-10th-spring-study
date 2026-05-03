package com.example.umc10th.domain.inquiry.entity;

import com.example.umc10th.domain.inquiry.enums.InquiryStatus;
import com.example.umc10th.domain.inquiry.enums.InquiryType;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "inquiry")
public class Inquiry extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inquiry_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "title", nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private InquiryType type;

    @Lob
    @Column(name = "content", nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    @Builder.Default
    private InquiryStatus status =  InquiryStatus.PENDING;

    @OneToMany(mappedBy = "inquiry", cascade = CascadeType.REMOVE)
    private List<InquiryImage> inquiryImageList = new ArrayList<>();

    @OneToOne(mappedBy = "inquiry", cascade = CascadeType.REMOVE)
    private InquiryReply inquiryReply;
}
