package com.alabtaal.thecomputershop.controller;


import com.alabtaal.thecomputershop.entity.ItemEntity;
import com.alabtaal.thecomputershop.mapper.ItemMapper;
import com.alabtaal.thecomputershop.model.ItemModel;
import com.alabtaal.thecomputershop.payload.response.ApiResponse;
import com.alabtaal.thecomputershop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "item")
public class ItemController {

    private final ItemService itemService;

    private final ItemMapper itemMapper;



    @PostMapping(value = "save")
    public ResponseEntity<ApiResponse<ItemModel>> save(@RequestBody ItemModel model){


        ItemEntity entity = itemMapper.toEntity(model);
        ItemModel savedData =   itemMapper.toModel(itemService.save(entity));

        ApiResponse<ItemModel> itemSavedSuccessfully = ApiResponse
                .<ItemModel>builder()
                .entity(savedData)
                .success(true)
                .message("ITEM SAVED SUCCESSFULLY.")
                .build();


        return ResponseEntity.ok(itemSavedSuccessfully);

    }


    @PutMapping(value = "update/{itemId}")
    public ResponseEntity<ApiResponse<ItemModel>> update(@RequestBody ItemModel model , @PathVariable  final UUID itemId){

        ItemEntity entity = itemMapper.toEntity(model);


        ItemModel savedData =   itemMapper.toModel(itemService.update(entity , itemId));

        ApiResponse<ItemModel> itemSavedSuccessfully = ApiResponse
                .<ItemModel>builder()
                .entity(savedData)
                .success(true)
                .message("ITEM UPDATED SUCCESSFULLY.")
                .build();


        return ResponseEntity.ok(itemSavedSuccessfully);

    }



    @GetMapping("items")
    public ResponseEntity<ApiResponse<List<ItemModel>>> findAll(){



        List<ItemModel> models = itemMapper.toModels(itemService.findAll()) ;

        ApiResponse<List<ItemModel>> items = ApiResponse
                .<List<ItemModel>>builder()
                .entity(models)
                .success(true)
                .message("ITEMS FETCHED SUCCESSFULLY.")
                .build();


        return ResponseEntity.ok(items);

    }


    @GetMapping(value = "find/{itemId}")
    public ResponseEntity<ApiResponse<ItemModel>> findById(@PathVariable  final UUID itemId){

        ItemModel savedData =   itemMapper.toModel(itemService.findById(itemId));

        ApiResponse<ItemModel> itemSavedSuccessfully = ApiResponse
                .<ItemModel>builder()
                .entity(savedData)
                .success(true)
                .message("ITEM FETCHED SUCCESSFULLY.")
                .build();


        return ResponseEntity.ok(itemSavedSuccessfully);

    }

    @DeleteMapping(value = "delete/{itemId}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable  final UUID itemId){

        itemService.deleteById(itemId);

        ApiResponse<String> response = ApiResponse
                .<String>builder()
                .success(true)
                .message("ITEM DELETED SUCCESSFULLY.")
                .build();


        return ResponseEntity.ok(response);

    }

}
