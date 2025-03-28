console.log('안녕');

function userSubmit() {
   //아이디
   let uid = document.getElementById('uid'); // 오타 수정: getElementbyId -> getElementById
    if (uid.value == "") {
        alert("아이디를 입력하세요."); // 아이디가 비어 있을 경우 경고 메시지 출력
        uid.focus(); // 아이디 입력 필드에 포커스를 이동
        return false; // 폼 제출 방지
    } else {
        // 아이디가 6~12글자의 영문 대소문자와 숫자로 구성되어 있는지 정규식을 통해 검사
        let exp = /^[A-Za-z0-9]{6,12}$/;
        if (!exp.test(uid.value)) {
              alert("잘못된 입력입니다.\n영문 대/소문자 및 숫자 6~12글자 이내로 작성해 주세요."); // 유효하지 않은 입력 경고
            uid.value = ""; // 잘못된 입력이므로 필드 초기화
            uid.focus(); // 아이디 입력 필드에 포커스 이동
            return false; // 폼 제출 방지
        }
    }

   //비밀번호
   let pwChk = document.getElementById('pwChk'); 
    
    //console.log(pwChk.value == 0);
    //console.log(pwChk.value == '0'); 둘다 호환
    
    if (pwChk.value == 0) {
       alert("비밀번호를 확인해 주세요"); // 유효하지 않은 입력 경고
       return false;
    }
    
    //이름
    let uname = document.getElementById('uname'); // 오타 수정: getElementbyId -> getElementById
   // 데이터 유무 확인
   if (uname.value == "") {
        alert("이름을 입력하세요."); // 이름이 비어 있을 경우 경고 메시지 출력
        uname.focus(); // 이름 입력 필드에 포커스를 이동
        return false; // 폼 제출 방지
    } else {
        // 이름이 2~5글자의 한글로 구성되어 있는지 정규식을 통해 검사
        let exp = /^[가-힣]{2,5}$/;
        if (!exp.test(uname.value)) {
            alert("잘못된 입력입니다.\n한글 2~5글자 이내로 작성해 주세요."); // 유효하지 않은 입력 경고
            uname.value = ""; // 잘못된 입력이므로 필드 초기화
            uname.focus(); // 이름 입력 필드에 포커스 이동
            return false; // 폼 제출 방지
        }
    }
    
    
    //이메일
    let uemail = document.getElementById('uemail');
    if (uemail.value!=''){
      // 이메일 형식 확인 정규 표현식
       let exp = /^[a-zA-Z0-9+-\_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;
       
       if (!exp.test(uemail.value)) {
           alert("잘못된 이메일 형식입니다. 다시 입력해 주세요.");           
           uemail.focus(); // 이메일 입력 필드에 포커스 이동
           return false; // 폼 제출 방지
       }
   }
    
 
    
    // 전화번호
    let uphone = document.getElementById('uphone'); 
    if (uphone.value == "") {
        alert("전화번호를 입력하세요."); // 전화번호가 비어 있을 경우 경고 메시지 출력
        uphone.focus(); // 전화번호 입력 필드에 포커스 이동
        return false; // 폼 제출 방지
    } else {
       // 전화번호 정규표현식: 010-0000-0000 또는 02-000-0000 형식 확인
        let exp = /^(\d{2,3})(\d{3,4})(\d{4})$/;
        if (!exp.test(uphone.value)) {
            alert("잘못된 입력입니다.\n전화번호를 다시 입력해 주세요\n예) 010-0000-0000 또는 02-000-0000");
            uphone.value = ''; // 잘못된 입력이므로 필드 초기화
            uphone.focus(); // 전화번호 입력 필드에 포커스 이동
            return false; // 폼 제출 방지
        }
    }


   // 주민번호 
   let ubirth = document.getElementById('ubirth'); 
    if (ubirth.value == "") {
        alert("생년월일을 입력해 주세요."); // 생년월일이 비어 있을 경우 경고 메시지 출력
        ubirth.focus(); // 생년월일 입력 필드에 포커스 이동
        return false; // 폼 제출 방지
    } else {
        let today = new Date(); // 현재 날짜
        let birthDate = new Date(ubirth.value); // 사용자가 입력한 생년월일

        // 생년월일이 미래일 경우 경고 메시지 출력
        if (birthDate > today) {
            alert("생년월일은 미래일 수 없습니다.\n다시 입력해 주세요.");
            ubirth.focus(); // 생년월일 입력 필드에 포커스 이동
            return false; // 폼 제출 방지  
        }

        // 생년월일이 100년 이상 과거일 경우 경고 메시지 출력
        let hundredYearsAgo = new Date(today.getFullYear() - 100, today.getMonth(), today.getDate());
        if (birthDate < hundredYearsAgo) {
            alert("나이가 100살이 넘어갑니다ㅠ...\n너무 오래살았군요...\n다시 입력해 주세요.");
            ubirth.focus(); // 생년월일 입력 필드에 포커스 이동
            return false; // 폼 제출 방지  
        }
    }  
   
   document.userForm.method = "POST";
    document.userForm.action = '/user/join';
    document.userForm.submit();
}

   //주소
   function postSearch() {
      new daum.Postcode({
           oncomplete: function(data) {
               // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
               // 예제를 참고하여 다양한 활용법을 확인해 보세요.
               //console.log(data);
               console.log('우편번호 >> ' + data.zonecode);
               console.log('도로주소 >> ' + data.roadAddress);
               document.getElementById('upost').value = data.zonecode;
               document.getElementById('uaddr1').value = data.roadAddress;
           }
         }).open();
   }//END OF postSearch() 