
function changeprofile() {
    const jspprofile = document.getElementById('jspprofile');
    const jspsaveprofile = document.getElementById('jspsaveprofile');
    

    if (jspprofile.files.length > 0) {
        jspsaveprofile.value = jspprofile.files[0].name;  
    } else {
        jspsaveprofile.value = "프로필사진";
    }
}

function updatephoto() {
    const jspprofile = document.getElementById('jspprofile');
    const jspsaveprofile = document.getElementById('jspsaveprofile');

       
    if (jspprofile.files.length > 0) {
        jspsaveprofile.value = jspprofile.files[0].name;  
    } else {
        jspsaveprofile.value = "프로필사진";
    }
    document.userForm.submit();
}

document.addEventListener('DOMContentLoaded', function() {
    document.querySelector('.emailchk').style.display = 'none';

    document.getElementById('emailCheckButton').addEventListener('click', function(event) {
        event.preventDefault();

        let umail = document.getElementById('umail');
        let emailExp = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
      
        if (umail.value.trim() === '') {
    alert("이메일을 입력해주세요.");
    umail.focus();
    return false;
   }
   if (!emailExp.test(umail.value)) {
       alert("잘못된 이메일 형식입니다.");
       umail.focus();
       return false;
   }

        alert("이메일이 유효합니다. 인증번호를 입력해주세요.");
        document.querySelector('.emailchk').style.display = 'flex';
    });
});

   function duplidchk(){
      const uid = document.getElementById('uid').value;
    if (uid.trim() === "") {
        alert("아이디를 입력하세요.");
        uid.focus();
        return false;
    } 
    let exp = /^[A-Za-z0-9]{6,12}$/;
    if (!exp.test(uid)) {
        alert("아이디는 영문 대소문자와 숫자 6~12자만 가능합니다.");
        uid.focus();
        return false;
    }
    console.log(uid);
    
    $.ajax({
       url:'/you_have_brother',
       data:{uid: uid},
       type:'POST',
         success: function(result){
           let msg = "${msg}";
            // 성공 시 결과값 리턴(result)이 잘 되었을 경우
            if(result.trim() === "no"){
               console.log("에러");
               alert("이미 존재하는 아이디입니다.");
            }else{
               console.log("성공");
               alert("사용할 수 있는 아이디입니다.");
               $("dupl").val("1");
            }
       }
       
    
   });
   }

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
   
   function uppostSearch() {
      new daum.Postcode({
           oncomplete: function(data) {
               // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
               // 예제를 참고하여 다양한 활용법을 확인해 보세요.
               //console.log(data);
               console.log('우편번호 >> ' + data.zonecode);
               console.log('도로주소 >> ' + data.roadAddress);
               document.getElementById('modalpost').value = data.zonecode;
               document.getElementById('modaladdr1').value = data.roadAddress;
           }
         }).open();
   }//END OF postSearch() 
   
   function userSubmit() {
    
    let dupl = document.getElementById('dupl');
    if(dupl === 0){
       alert('아이디 중복체크를 확인해주세요!');
       uid.focus();
       return false;
    }

    let upw = document.getElementById('upw');
    if (upw.value.trim() === '') {
        alert('패스워드를 입력해주세요.');
        upw.focus();
        return false;
    } 
    let pwExp = /^(?=.*?[a-z])(?=.*?[0-9])(?=.*?[~?!@#$%^&*_-]).{8,}$/;
    let pwExp2 = /(\w)\1\1\1/;
    if (!pwExp.test(upw.value) || pwExp2.test(upw.value)) {
        alert('패스워드는 영어, 숫자, 특수기호를 포함하고, 같은 문자는 4번 이상 반복할 수 없습니다.');
        upw.focus();
        return false;
    }

    let uname = document.getElementById('uname');
    let nameExp = /^[가-힣]{2,5}$/;
    if (uname.value.trim() === "" || !nameExp.test(uname.value)) {
        alert("이름은 한글 2~5자로 작성해 주세요.");
        uname.focus();
        return false;
    }

    let umail = document.getElementById('umail');
    let emailExp = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    if (umail.value.trim() === '') {
    alert("이메일을 입력해주세요.");
    umail.focus();
    return false;
   }

    const mailchk = Number(document.getElementById('mailChk').value);
    if(mailchk === 0){
        alert("이메일 확인을 진행해주세요!");
        document.getElementById('mailChk').focus();
        return false;
    }

    // 전화번호 입력 확인
    const uphone1 = document.getElementById('uphone1').value;
    const uphone2 = document.getElementById('uphone2').value.trim();
    const uphone3 = document.getElementById('uphone3').value.trim();

    if (uphone2 === "" || uphone3 === "") {
        alert("전화번호를 모두 입력해주세요.");
        return false;
    }

    const phoneRegex = /^\d{4}$/;
    if (!phoneRegex.test(uphone2) || !phoneRegex.test(uphone3)) {
        alert("전화번호는 각각 4자리 숫자로 입력해주세요.");
        return false;
    }

    const uphone = uphone1 + uphone2 + uphone3;
    document.getElementById('uphone').value = uphone;

    // 생년월일 입력 확인
    const frontbirth = document.getElementById('frontbirth').value;
    if (!frontbirth) {
        alert("생년월일을 입력해주세요.");
        return false;
    }
    
    // 성별 선택 확인
    const ugender = document.querySelector('input[name="ugender"]:checked');
    if (!ugender) {
        alert("성별을 선택하세요.");
        return false;
    }

    // 주소 입력 확인
    const upost = document.getElementById('upost').value;
    const uaddr2 = document.getElementById('uaddr2').value;

    if(upost === ""){
        alert("주소를 입력하세요");
        return false;
    }
    if(uaddr2 === ""){
        alert("상세주소를 입력하세요");
        return false;
    }

    const ubirth = frontbirth.replace(/-/g, '').slice(2);  // YYMMDD로 변환
    document.getElementById('ubirth').value = ubirth;


    console.log("폼 제출 준비 완료 ");
    document.forms['userform'].submit();
}
   
function modalpassword(){
   document.querySelector('.modal.pw').classList.add('is-show');
}

function updatepassword(){
    let uid = $("#uid").val();
    let upupw = $("#upupw").val();
    let upupw2 = $("#upupw2").val();
    console.log(uid);
    console.log(upupw);
    console.log(upupw2);
    if (upupw === '') {
        alert('패스워드를 입력해주세요.');
        $("#upupw").focus();
        return false;
    } 
    //let pwExp = /^(?=.*?[a-z])(?=.*?[0-9])(?=.*?[~?!@#$%^&*_-]).{8,}$/;
    let pwExp = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*?_]).{8,}$/;
    let pwExp2 = /(\w)\1\1\1/;
    if (!pwExp.test(upupw)){
        alert('패스워드는 영어,숫자,특수기호를 포함하고, 같은 문자는 4번 이상 반복할 수 없습니다.');
        $("#upupw").focus();
        return false;
    }
    if( pwExp2.test(upupw)) {
        alert('2패스워드는 영어,숫자,특수기호를 포함하고, 같은 문자는 4번 이상 반복할 수 없습니다.');
        $("#upupw2").focus;
        return false;
    }
    if(upupw != upupw2){
       alert('두 패스워드가 일치하지 않습니다.')
       $("#upupw").focus();
       return false;
    }

    // 비동기 방식 -> ajax
    // 경로, 데이터, 데이터 전송 방식, 성공 or 실패
    $.ajax({
        url:'/User_Update-password',
        data: {
            uid: uid
            ,upw: upupw
        },
        type: 'POST',
        success: function(result){
           let msg = "${msg}";
           console.log(msg);
           console.log(result);
           console.log(msg);
            // 성공 시 결과값 리턴(result)이 잘 되었을 경우
            if(result == 0){
               console.log("에러");
               alert("비밀번호 수정 실패");
               //if(msg == "uppw_OK"){alert('정보수정 성공');}
            }else{
               console.log("성공");
               alert("비밀번호 수정 성공");
               window.location.href = "/";
               //if(msg == "uppw_ERR"){alert('정보수정 실패');}
            }
        }
    });
}; // end of password ajax

/* 회원 정보 수정 */
function updatephone(){
   document.querySelector('.modal.info').classList.add('is-show');
   const modalphone = document.getElementById('modalphone');
   console.log(modalphone);
   modalphone.type="text";
   modalphone.placeholder="전화번호를 입력하세요";
   modalphone.value="";
};

function updatemail(){
   document.querySelector('.modal.info').classList.add('is-show');
   const modalmail = document.getElementById('modalmail');
   console.log(modalmail);
   modalmail.type="text";
   modalmail.placeholder="메일을 입력하세요";
   modalmail.value="";
};

function updateaddr(){
   document.querySelector('.modal.info').classList.add('is-show');
   const modalpost = document.getElementById('modalpost');
   const addrbutton = document.getElementById('addrbutton');
   const modaladdr1 = document.getElementById('modaladdr1');
   const modaladdr2 = document.getElementById('modaladdr2');
   
   modalpost.type = "text";
   addrbutton.type = "button";
   modaladdr1.type = "text";
   modaladdr2.type = "text";
   modaladdr2.placeholder = "상세주소를 입력하세요";

   uppostSearch();
}


function updateinfo() {
    const form = document.getElementById('modalinfo'); 
    const formData = new FormData(form);

    formData.append("uid", document.getElementById('modalid').value);
    formData.append("upost", document.getElementById('modalpost').value);
    formData.append("uaddr1", document.getElementById('modaladdr1').value);
    formData.append("uaddr2", document.getElementById('modaladdr2').value);

    console.log("전송할 데이터 확인:");
    for (let pair of formData.entries()) {
        console.log(pair[0] + ": " + pair[1]);
    }
	for (let pair of formData.entries()) {
    console.log(`📝 ${pair[0]}: ${pair[1]}`);
	}

    $.ajax({
        url: '/User_Update-info',
        data: formData,
        type: 'POST',
        contentType: false,   // false 설정 (FormData 사용 시 필요)
        processData: false,   // false 설정 (FormData 사용 시 필요)
        enctype: 'multipart/form-data',
        cache: false,
        success: function(result) {
            console.log("서버 응답:", result);
            if (result == "0") {
                console.log("에러 발생");
                alert("회원정보 수정 실패");
            } else {
                console.log("성공");
                alert("회원정보 수정 성공");
                window.location.href = "/";
            }
        },
        error: function(xhr, status, error) {
            console.error("AJAX 요청 실패:", status, error);
            alert("서버 오류 발생! 다시 시도해주세요.");
        }
    });
}

   
