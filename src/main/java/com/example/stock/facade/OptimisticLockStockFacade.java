package com.example.stock.facade;

import com.example.stock.service.OptimisticLockStockService;
import org.springframework.stereotype.Component;

@Component
public class OptimisticLockStockFacade {
    private final OptimisticLockStockService optimisticLockStockService;

    public OptimisticLockStockFacade(OptimisticLockStockService optimisticLockStockService) {
        this.optimisticLockStockService = optimisticLockStockService;
    }

    public void decrease(Long id, Long quantity) throws InterruptedException {
        // 실패시 재실행을 위한 로직
        while(true) {
            try {
                optimisticLockStockService.decrease(id, quantity);

                break;
            }
            catch (Exception e){
                Thread.sleep(50);
            }
        }
    }
}
