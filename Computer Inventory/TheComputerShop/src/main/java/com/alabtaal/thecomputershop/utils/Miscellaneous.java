package com.alabtaal.thecomputershop.utils;



import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class Miscellaneous {

    private static Validator validator;


    public Miscellaneous(Validator validator) {
        Miscellaneous.validator = validator;
    }


    public static <T> void constraintViolation(final T clazz) throws BadRequestException {
        Set<ConstraintViolation<T>> validate = validator.validate(clazz);
        if (!CollectionUtils.isEmpty(validate)) {
            String constraintMassage = validate.stream()
                    .map(ConstraintViolation::getMessage).collect(Collectors.joining(" \n "));
            throw new BadRequestException(constraintMassage);
        }

    }


    public static Pageable validatePage(final String pageNumber , final String pageSize , final String sortBy , final String sortDirection){
        int pageNum;
        int pageSiz;
        try {
             pageNum = Integer.parseInt(pageNumber);
             pageSiz = Integer.parseInt(pageSize);
        }catch (NumberFormatException e){
            throw new NumberFormatException(e.getMessage());
        }
        Sort.Direction asc = sortDirection.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(asc , sortBy);



        return PageRequest.of(pageNum, pageSiz , sort);
    }


}
