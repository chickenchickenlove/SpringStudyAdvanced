# SpringStudyAdvanced
김영한님의 스프링 핵심원리 강의를 듣고 정리하는 곳입니다.


# 22.01.22
- (강의) 예제 프로젝트 만들기 - V0
- (강의) 로그 추적기 요구사항 분석
- (강의) 로그 추적기 V1 - 프로토타입 개발
- (강의) 로그 추적기 V1 - 적용
- (강의) 로그 추적기 V2 - 파라미터로 동기화 개발
- (강의) 로그 추적기 V2 - 적용
- (강의) 필드 동기화 - 개발
- (강의) 필드 동기화 - 적용
- (강의) 필드 동기화 - 동시성 문제
- (강의) 동시성 문제 - 예제 코드
- (강의) ThreadLocal 소개
- (강의) ThreadLocal 예제 코드
- (강의) ThreadLocal 로컬 동기화 개발
- (강의) ThreadLocal 로컬 동기화 적용
- (강의) ThreadLocal 사용 시, 주의 사항

# 22.01.25
- (강의) 템플릿 메서드 패턴 - 시작
- (강의) 템플릿 메서드 패턴 - 예제1,2,3
- (강의) 템플릿 메서드 패턴 - 적용1,2
- (강의) 템플릿 메서드 패턴 - 정의
- (강의) 전략 패턴 - 예제1,2,3
- (강의) 템플릿 콜백 패턴 - 예제, 적용 
- (코드) 로그 추적기 V1 개발 및 적용
- (코드) 로그 추적기 V2 개발 및 적용 (파라미터로 동기화)
- (코드) FieldLogTrace 개발(필드 동기화 개발) → 동시성 문제 발견
- (코드) ThreadLocalLogTrace 개발 → 동시성 문제 해결 
- (정리) 로그 추적기 V1,V2, FieldLogTrace, ThreadLocalLogTrace 개발 관련 내용 정리 (https://ojt90902.tistory.com/696)
- (정리) 동시성 문제와 쓰레드 로컬 (https://ojt90902.tistory.com/697)
- (코드) Template 패턴 학습을 위한 테스트 코드 작성 및 실행
- (코드) 로그 추적기에 Template 패턴을 적용한 코드 리팩토링 (V4 Controller, Repository, Service)


# 22.01.26
- (강의) 프록시 패턴 + 데코레이터 패턴 작성을 위한 예제 프로젝트 작성(v1,v2,v3)
- (강의) 프록시, 프록시 패턴, 데코레이터 패턴 소개
- (강의) 테스트 코드를 통한 데코레이터 패턴 확인
- (강의) 테스트 코드를 통한 프록시 패턴 확인
- (강의) 인터페이스 기반 프록시 패턴 실제 코드 적용
- (강의) 구체 클래스 기반 프록시 패턴 실제 코드 적용
- (코드) Strategy 패턴 테스트 코드 작성
- (코드) Template-CallBack 패턴 테스트 코드 작성
- (코드) Template-CallBack 패턴을 적용한 LogTrace 기능 구현 
- (정리) 템플릿 메서드 패턴, 전략 패턴, 템플릿 콜백 패턴의 이해 및 정리(https://ojt90902.tistory.com/698)
- (코드) 프록시 패턴 테스트 코드로 이해, 데코레이터 패턴 테스트 코드로 이해


# 22.01.27
- (강의) 동적 프록시 기술(리플렉션, JDK 동적 프록시, CGLIB 동적 프록시)
- (강의) 스프링이 지원하는 프록시(프록시 팩토리, 포인트컷, 어드바이스, 어드바이저, 직접 만든 포인트컷, 스프링이 제공하는 포인트컷)
- (강의) 프록시 팩토리 (여러 어드바이저 함께 적용) 
- (코드) 프록시 패턴 + 데코레이터 패턴 복습.
- (코드) 데코레이터 패턴의 구현 / 상속을 통해서 각각 로그 추적기 적용
- (코드) 리플렉션 테스트 코드 작성
- (코드) JDK 동적 프록시 테스트 코드 작성. JDK 동적 프록시 기술을 이용한 로그 추적기 적용
- (코드) CGLIB 프록시 테스트 코드 작성. 
- (코드) 프록시 팩토리를 사용한 JDK 동적 프록시, CGLIB 프록시 테스트 코드 작성
- (코드) 프록시 팩토리를 사용한 Advise 추가, PointCut 직접 만들어 등록하는 테스트 코드 작성
- (코드) 프록시 팩토리를 이용한 Advisor 사용법 확인. 다중 Advisor 사용(프록시 여러개 만드는 것 + addAdvisors로 최소화)


# 22.01.28
- (강의) 빈 후처리기 예제 코드 적용, 빈 후처리기를 로그 추적기에 적용하기
- (강의) 스프링이 제공하는 빈 후처리기1,2 사용하기. 하나의 프록시 여러 어드바이저에 적용해보기 
- (강의) @Aspect 프록시 적용 및 설명
- (강의) 스프링 AOP 개념(핵심 기능 + 부가 기능 / Aspect / AOP 적용 방식 / AOP 용어 정리)
- (코드) 프록시 인터페이스 구현을 통한 횡단 관심사 로그 추적기에 적용
- (코드) JDK 동적 프록시를 이용한 횡단 관심사 로그 추적기 적용
- (코드) ProxyFactory를 이용한 횡단 관심사 로그 추적기 적용
- (코드) AnnotationAwareAutoProxyCreator를 이용한 횡단 관심사 로그 추적기 적용
- (코드) @Aspect + @Around를 이용한 횡단 관심사 로그 추적기 적용.
- (정리) 프록시 패턴 / 데코레이터 패턴 / 프록시를 적용한 횡단 관심사 해결(https://ojt90902.tistory.com/699)
- (정리) JDK 동적 프록시, CGLIB를 이용한 동적 프록시 구현 및 로그 추적기에 적용(https://ojt90902.tistory.com/700)


# 22.01.29
- (강의) 스프링 AOP(시작, 포인트컷 분리, 어드바이스 추가, 포인트컷 참조, 어드바이스 순서, 어드바이스 종류)
- (코드) ProxyFactory를 이용한 횡단 관심사 처리 / BeanPostProcessor를 이용한 Component Scan 대상에 횡단 관심사 처리
- (코드) AnnotationAwareAutoProxyCreator를 이용한 횡단 관심사 처리, @Aspect Annotation Advisor를 이용한 횡단 관심사 처리
- (정리) 프록시 팩토리를 이용한 횡단 관심사 처리(https://ojt90902.tistory.com/701)
- (정리) 빈 후처리기(Bean PostProcessor)를 이용한 횡단 관심사 처리(https://ojt90902.tistory.com/702)


# 22.01.30
- (정리) 스프링이 제공하는 빈 후처리기 AnnotationAwareAspectJAutoProxyCreator를 이용한 횡단 관심사 처리(https://ojt90902.tistory.com/703)


# 22.01.31
- (강의) 스프링 AOP 포인트컷 지시자 사용법(execution,within,args,@target,@within,@annotation,@args, bean, 매개변수 전달) 
- (강의) 스프링 AOP 포인트컷 지시자 관련. this / target 포인트컷 지시자의 프록시 생성에서의 차이.
- (강의) 스프링 AOP 실제 예제 작성 및 적용 (재시도 AOP, 로그 추적기 AOP)
- (강의) 스프링 AOP 프록시와 내부 호출문제 → Self DI, 지연 조회, 구조 변경등으로 해결. 프록시 기술과 한계(타입 캐스팅, DI, CGLIB등)
- (코드) @Aspect를 이용한 테스트 코드 작성(@PointCut 이용, @PointCut 분리, 어드바이저 실행 순서 변경,@Around 외의 어드바이스 사용 테스트 코드)

# 22.02.02
- (정리) @Aspect를 이용한 AOP 프록시 적용 및 코드 리팩토리 관련 정리(https://ojt90902.tistory.com/704)


# 22.02.04
- (코드) Pointcut 지시자를 이용한 테스트 코드 작성(exeuction, within, target, args, @annotation, @within, @target, this, target)
- (정리) Pointcut 지시자 용법 정리(https://ojt90902.tistory.com/707)


# 22.02.06
- (정리, 코드) @Aspect를 실제 프로젝트에 적용하기 (로그 추적기, 재시도하기) (https://ojt90902.tistory.com/718)
