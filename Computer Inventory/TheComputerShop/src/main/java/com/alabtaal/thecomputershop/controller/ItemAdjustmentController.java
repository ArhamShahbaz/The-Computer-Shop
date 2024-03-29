package com.alabtaal.thecomputershop.controller;


import com.alabtaal.thecomputershop.model.ItemAdjustmentModel;
import com.alabtaal.thecomputershop.payload.response.ApiResponse;
import com.alabtaal.thecomputershop.service.ItemAdjustmentService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "item/adjustment")
public class ItemAdjustmentController {

    private final ItemAdjustmentService itemAdjustmentService;





    @PostMapping(value = "save")
    public ResponseEntity<ApiResponse<ItemAdjustmentModel>> save(@RequestBody ItemAdjustmentModel model) throws BadRequestException {


        
        ItemAdjustmentModel savedData =   itemAdjustmentService.save(model);

        ApiResponse<ItemAdjustmentModel> itemSavedSuccessfully = ApiResponse
                .<ItemAdjustmentModel>builder()
                .entity(savedData)
                .success(true)
                .message("ITEM ADJUSTMENT SAVED SUCCESSFULLY.")
                .build();


        return ResponseEntity.ok(itemSavedSuccessfully);

    }


    @PutMapping(value = "update/{adjustmentId}")
    public ResponseEntity<ApiResponse<ItemAdjustmentModel>> update(@RequestBody ItemAdjustmentModel model , @PathVariable  final UUID adjustmentId) throws BadRequestException {




        ItemAdjustmentModel savedData =   itemAdjustmentService.update(model , adjustmentId);

        ApiResponse<ItemAdjustmentModel> itemSavedSuccessfully = ApiResponse
                .<ItemAdjustmentModel>builder()
                .entity(savedData)
                .success(true)
                .message("ITEM ADJUSTMENT UPDATED SUCCESSFULLY.")
                .build();


        return ResponseEntity.ok(itemSavedSuccessfully);

    }



    @GetMapping("adjustments")
    public ResponseEntity<ApiResponse<List<ItemAdjustmentModel>>> findAll(){



        List<ItemAdjustmentModel> models = itemAdjustmentService.findAll() ;

        ApiResponse<List<ItemAdjustmentModel>> items = ApiResponse
                .<List<ItemAdjustmentModel>>builder()
                .entity(models)
                .success(true)
                .message("ITEM ADJUSTMENTS FETCHED SUCCESSFULLY.")
                .build();


        return ResponseEntity.ok(items);

    }


    @GetMapping(value = "find/{adjustmentId}")
    public ResponseEntity<ApiResponse<ItemAdjustmentModel>> findById(@PathVariable  final UUID adjustmentId){

        ItemAdjustmentModel savedData =  itemAdjustmentService.findById(adjustmentId);

        ApiResponse<ItemAdjustmentModel> itemSavedSuccessfully = ApiResponse
                .<ItemAdjustmentModel>builder()
                .entity(savedData)
                .success(true)
                .message("ITEM ADJUSTMENT FETCHED SUCCESSFULLY.")
                .build();


        return ResponseEntity.ok(itemSavedSuccessfully);

    }

    @DeleteMapping(value = "delete/{adjustmentId}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable  final UUID adjustmentId){

        itemAdjustmentService.deleteById(adjustmentId);

        ApiResponse<String> response = ApiResponse
                .<String>builder()
                .success(true)
                .message("ITEM ADJUSTMENT DELETED SUCCESSFULLY.")
                .build();


        return ResponseEntity.ok(response);

    }

}
