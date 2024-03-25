package com.catale.backend.domain.review.repository.custom;

import com.catale.backend.domain.cocktail.dto.CocktailListResponseDto;
import com.catale.backend.domain.review.dto.ReviewGetResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.catale.backend.domain.cocktail.entity.QCocktail.cocktail;
import static com.catale.backend.domain.review.entity.QReview.review;

@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom{
    private final JPAQueryFactory query;

    @Override
    public Optional<List<ReviewGetResponseDto>> findByCocktailId(Long cocktailId) {
        return Optional.ofNullable(query.select(Projections.constructor(ReviewGetResponseDto.class, review.id,review.cocktail.id,review.member.id, review.content, review.rate, review.sweet, review.bitter, review.sour, review.sparking, review.createdAt))
                .from(review)
                .where(review.cocktail.id.eq(cocktailId)
                        .and(review.isDeleted.eq(false)))
                .fetch());
    }

}