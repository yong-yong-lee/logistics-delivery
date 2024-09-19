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


<div align="right">

[목차](#목차)


</div>



## 10. 트러블 슈팅

<div align="right">

[목차](#목차)

</div>
