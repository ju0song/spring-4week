package sparta.week4.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import sparta.week4.Dto.ProductMypriceRequestDto;
import sparta.week4.Dto.ProductRequestDto;
import sparta.week4.Entity.Product;
import sparta.week4.Security.UserDetailsImpl;
import sparta.week4.Service.ProductService;

import java.util.List;

@RestController // JSON으로 데이터를 주고받음을 선언합니다.
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // 신규 상품 등록
    // Controller에서 로그인 회원정보를 받아 Service로 전달
    @PostMapping("/api/products")
    public Product createProduct(@RequestBody ProductRequestDto requestDto,
                                 //@AuthenticationPrincipal UserDetailsImpl userDetails 로그인 성공한 사용자의 정보가 들어옴
                                 @AuthenticationPrincipal UserDetailsImpl userDetails) {
// 로그인 되어 있는 회원 테이블의 ID
//        getUser().getId(); ->로그읜 성공한 사용자의 user에서 id를 가져옴
        Long userId = userDetails.getUser().getId();
//신규상품등록하는것은 productService 라서 userID와 dto를 넘겨줌
        Product product = productService.createProduct(requestDto, userId);

// 응답 보내기
        return product;
    }

    // 설정 가격 변경
    @PutMapping("/api/products/{id}")
    public Long updateProduct(@PathVariable Long id, @RequestBody ProductMypriceRequestDto requestDto) {
        Product product = productService.updateProduct(id, requestDto);

// 응답 보내기 (업데이트된 상품 id)
        return product.getId();
    }

    // 로그인한 회원이 등록한 관심 상품 조회
//    모든상품에대한 조회가아닌 로그인되어져있는 회원의 ID가 필요
    @GetMapping("/api/products")
    public List<Product> getProducts(@AuthenticationPrincipal UserDetailsImpl userDetails) {
// 로그인 되어 있는 회원 테이블의 ID
        Long userId = userDetails.getUser().getId();

        return productService.getProducts(userId);
    }
}