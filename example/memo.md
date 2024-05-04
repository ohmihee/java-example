Q) 데이터 등록 시 자동으로 createdDateTime 들어가도록 하는 @CreatedDate @LastModifiedDate 가 적용되지 않는 ㄱ여우 
-> 부트 어플리케이션 클래스에 @EnableJpaAuditing 추가해주고, 엔티티 클래스에 @EntityListeners(AuditingEntityListener.class) 를 추가하여 준다.