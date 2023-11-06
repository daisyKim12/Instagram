# Instagram

<day 1> 3/4
- git 등록 - login 화면 구성 
- edittext의 hint부분을 위 아래로 움직이게 하기 위해 text input layout 사용 hint 색상과 다양한 custom은 style에서 조정
    <com.google.android.material.textfield.TextInputEditText
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="이메일..."
        />
    <com.google.android.material.textfield.TextInputLayout/>
- main activity toolbar, bottom nav 구성
- home fragment layout 구성
- recyclerview item layout 구성

<day 2> 3/5
- home fragment 스토리RV, 게시물RV 완성
- search fragment toolbar 크기 변하는 edittext 구현
- reels fragment layout 구현
- reels fragment 릴스RV 

<day 3> 3/6
- profile fragment layout 구성
- home fragment에 multiviewtype으로 story rv, 게시물 rv 한 곳에 담음
- home 게시물 item 안에 viewpager와 dot indicator 추가 및 중첩 rv사용
- profile fragment에 tablayout과 fragment viewpager 연동
- profile fragment collapsible toolbar로 layout 수정
- search fragment에 staggered grid recycler view 추가

<day 4> 3/7
- main activity 종속 fragement layout 수정
- 회원가입 activity 완성, sharedPref 사용
- Post activity, gallery recyclerview 중간 완성

<day 5> 3/8
- post activity 모두 완성
- 다중 선택 구현
- gallery에서 image가져와 rv 채우기 구현
- 회원가입 activity 모두 완성

<day 6> 3/9
- connecting camera
- connecting gallery
- Email Authirity API 연결 완료
- progress button + thread 연결 완료

<day 7>  3/10
- email 회원가입 관련 API 3개 연결
- phone 회원가입 관련 API 2개 연결
- 로그인 관련 API 2개 연결
- error dialog 완성
- path2uri, uri2path
- userPage Activity 

<day 8> 3/11
- 댓글 activity item layout 완성

<day 9> 3/12
- 게시물 등록 api 연결
- activity stack clear 완료
- 피드 불러오는 API 연결
- 내 프로필 불러오는 API 연결
- 내 프로필 작성한 게시물 API 연결
- search 전체 게시물 조회 API 연결

<day 10> 3/13
/좋아요 요청 API 연결 
/좋아요 취소 API 연결
/게시물 상세 불러오기 API 연결 @ search
/팔로우 요청 API 연결 
/팔로우 취소 API 연결
/게시물 상세 불러오기 API 연결 @ my profile
/게시물 상세 불러오기 API 연결 @ my profile

<day 11>
/대댓글 보기 api 연결
/대댓글 신청 api 연결
/댓글 신청 api 연결
/댓글 조회 api 연결
/bookmark 신청 api 연결
/bookmark 최소 api 연결
/팔로잉 api 연결 
/팔로워 api 연결
/search 화면 thumbnail, post 이미지 불일치 오류 해결
/게시물 업로드 api 수정
/bottom nav 프로필 이미지 넣기

<day 12>
/로그아웃 완료
/bottom nav style 완료
/unixtime to date format 완성
/post_activity layout 수정
/feed adapter에 댓글 activity, 댓글 API 연결
/feed api연결 /feed adapter에 다른 api연결 -좋아요 api연결 -북마크 api연결 -프로필 불러오기 api연결

