function onResponse(result){
  // 화면에 뿌려주기
  var commentListElement = document.getElementById('commentList');
  commentListElement.innerHTML = '';
  for(var i = 0; i<result.length; i++){
    var commentElement = document.createElement('p');
    commentElement.innerHTML = result[i].authorDisplayName + ": " + result[i].textOriginal;
    commentListElement.appendChild(commentElement);
  }
}

// 버튼 누르면
function doRaffle() {
  var commentListElement = document.getElementById('commentList');
  commentListElement.innerHTML = '<img src="loading.gif"/>';

  // link, count값을 읽어
  var link = document.getElementById('link').value;
  var count = document.getElementById('count').value;
  var url = '/api/' + link + '?count=' + count;
  if(count < 1){
    window.alert("최소 추첨 인원 수는 1명 이상이어야 합니다.");
  }
  else if(count > 10){
    window.alert("최대 추첨 인원 수는 10명 아래입니다.");
  }
  else{
  // api로 전송하고
    fetch(url)
      .then((result) => {
        return result.json();
      })
      .then(onResponse)
      .catch((err) => {
        console.log(err);
      });
  }
}