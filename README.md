# 🐉 용용이

### 목차
[1. 프로젝트 소개](#1-프로젝트-소개)  
[2. 개발 기간](#2-개발-기간)  
[3. 팀원 역할 분담](#3-팀원-역할-분담)  
[4. 개발 환경](#4-개발-환경)  
[5. 기능 명세서](#5-기능-명세서)  
[6. 서비스 구성](#6-서비스-구성)  
[7. 프로젝트 아키텍처](#7-프로젝트-아키텍처)  
[8. ERD 설계](#8-erd-설계)  
[9. API 명세](#9-api-명세)  
[10. 트러블 슈팅](#10-트러블-슈팅)

## 1. 프로젝트 소개
이 프로젝트는 SPRING BOOT 기반 MSA 구조의 B2B 물류 관리 및 배송 서비스입니다. <br>

A 기업에서 B 기업으로 발주되는 상품이 최종 도착할 때까지의 배송 상태를 관리할 수 있습니다.

<br>
<div align="right">

[목차](#목차)
</div>

## 2. 개발 기간

* 개발 기간: 09/05(목) ~ 09/19(목) (총 15일)
* 최종 발표: 09/20(금)

<div align="right">

[목차](#목차)

</div>

## 3. 팀원 역할 분담
- 김용재 (팀장) : BE / Auth, User 개발
- 김소이 (팀원) : BE / Hub, Vendor, Product 개발
- 김정용 (팀원) : BE / Order, Delivery 개발

<div align="right">

[목차](#목차)

</div>

## 4. 개발 환경
- **Java** : <img src = "https://img.shields.io/badge/Java 17-007396?&logo=java&logoColor=white">
- **Framework** : <img src = "https://img.shields.io/badge/Springboot 3-6DB33F?&logo=springboot&logoColor=white">
- **IDE** : <img src = "https://img.shields.io/badge/Intellij Idea-000000?&logo=intellijidea&logoColor=white">
- **Database** :  <img src = "https://img.shields.io/badge/PostgreSQL-4479A1?&logo=PostgreSQL&logoColor=white">, <img src = "https://img.shields.io/badge/Redis-FF4438?&logo=redis&logoColor=white">
- **Meeting** : <img src = "https://img.shields.io/badge/Discord-5865F2?&logo=discord&logoColor=white">
- **Docs** : <img src = "https://img.shields.io/badge/Swagger-85EA2D?&logo=swagger&logoColor=white">, <img src = "https://img.shields.io/badge/Notion-000000?&logo=Notion&logoColor=white">
- **Version Controll** : <img src = "https://img.shields.io/badge/Git-F05032?&logo=git&logoColor=white">, <img src = "https://img.shields.io/badge/Github-181717?&logo=github&logoColor=white">

<div align="right">

[목차](#목차)

</div>

## 5. 기능 명세서

### 😄 사용자 로그인 및 회원가입
* 사용자는 로그인 및 회원가입을 통해 각자의 role을 갖고 서비스에 접근할 수 있습니다. <br>
* 초기에 설정된 role 에 따라 이후 접근할 수 있는 서비스에 차이가 있습니다.

<br>

### 🗺 허브 & 허브 간 경로 설정
* 관리자는 허브를 추가하고 허브간 경로를 설정할 수 있습니다. <br>
* 이후 해당 허브가 배송경로 안에 있는 경우 설정해놓은 에상 시간 및 예상 거리를 확인 할 수 있습니다. <br>
* 현재 배송이 어느 허브까지 완료되었지는지 확인할 수 있습니다.

<br>

### 🏭 업체 등록, 상품 등록
* 업체를 등록할 수 있으며 가까운 허브 id를 동시와 등록하게 됩니다. <br>
* 업체는 팔고싶은 상품을 등록할 수 있으며 기업 간 물건을 사고 팔 수 있습니다.

<br>

### 🚚 주문 및 배송 경로 확인
* 주문을 하게되면 경유하게되는 허브 정보를 포함한 배송 경로를 확인 할 수 있습니다. <br>
* 허브 간 배송상태를 확인 할 수 있어, 본인이 주문한 물건의 대략적인 위치를 알 수 있습니다.



<div align="right">
[목차](#목차)
</div>

## 6. 서비스 구성

```
eureka (localhost:19090)

gateway (localhost:19091)

user (localhost:19093/api/v1/auth, localhost:19093/api/v1/users)
└── domain
    └── User

hub (localhost:19095/api/v1/hubs, localhost:19095/api/v1/hub-route)
└── domain
    ├── Hub
    └── HubRoute

vendor (localhost:19096/api/v1/vendors, localhost:19096/api/v1/products)
└── domain
    ├── Vendor
    └── Product

order-service (localhost:19097/api/v1/orders, localhost:19097/api/v1/delivery)
└── domain
    ├── Order
    ├── Delivery
    └── DeliveryRoute
```

<div align="right">

[목차](#목차)

</div>

## 7. 프로젝트 아키텍처
![프로젝트_아키텍처](https://github.com/user-attachments/assets/baa11243-27d4-4c53-beab-1cedc102cd85)



<div align="right">

[목차](#목차)


</div>

## 8. ERD 설계
![image](https://github.com/user-attachments/assets/b7374a90-a778-4e0a-8968-8802871d373f)


<div align="right">

[목차](#목차)

</div>

## 9. api 명세

[API DOCS 바로가기](https://superficial-look-a79.notion.site/API-106b4439041280819148c0d9b2236d0b?pvs=4)

<div align="right">

[목차](#목차)


</div>



## 10. 트러블 슈팅

### <strong> hubRoute의 순서 보장 🤔 </strong>
   * 'hubRoute가 저장되는 순서를 어떻게 알고 가져와서 써야하나' 고민하게 되었습니다.
   데일리 스크럼시간에 해당 문제사항을 공유하고 팀원들과 의견을 공유하며 세가지의 방법이 나오게되었습니다.
   
   > 1) hubRoute 생성 시 index 역할을 할 속성값을 하나 추가하자 !
      첫번째로 그냥 간단하게 떠오른 생각이였습니다. 적용은 간단하지만, hubRoute가 중간에 새로 추가된다거나, 또는 삭제되면
      index가 섞여 오히려 더 복잡해지는 상황이 예상됐습니다. index를 수정하려면 모든 값들을 정비해야하는 단점 또한 존재합니다.
      
   > 2) C 언어의 pointer 처럼 이전hub, 다음hub를 가르키는 속성을 추가하자 !
      위의 단점을 보완할 수 있는 방법입니다. 이전 hub, 다음 hub를 추가해 따라가면서 hub를 연결 시킬 수 있습니다.
      hub의 변화가 생길 시 앞, 뒤 hub만 신경써서 변경시켜주면 되는 장점이 있지만 속성이 두개나 더 추가됩니다.

   > 3) hubRoute DB에서 모든 데이터를 가져와 완전탐색하여 사용하자 !
      채택된 방법입니다.
      튜터님에게도 조언을 구한 결과 hubRoute 데이터의 특성 상 데이터의 변화가 그렇게 크지는 않을 것이라는 판단으로
      hubRoute의 모든 경로를 불러와 완탐하는 방법을 처리했습니다.

   * 다음에는) hubRoute 부분에 cache 처리를 해보면 어떨까? 하는 생각을 하게 되었습니다.
   

<div align="right">

[목차](#목차)

</div>
