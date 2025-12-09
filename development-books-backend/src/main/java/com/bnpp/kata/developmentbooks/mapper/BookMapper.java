package com.bnpp.kata.developmentbooks.mapper;


import com.bnpp.kata.developmentbooks.dto.BookResponse;
import com.bnpp.kata.developmentbooks.store.BookEnum;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface BookMapper {

    BookResponse toResponse(BookEnum bookEnum);
}
