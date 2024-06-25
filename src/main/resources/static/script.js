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
  // link, count값을 읽어
  var link = document.getElementById('link').value;
  var count = document.getElementById('count').value;
  var reg = /(?:youtube(?:-nocookie)?\.com\/(?:[^\/\n\s]+\/\S+\/|(?:v|e(?:mbed)?)\/|\S*?[?&]vi?=)|youtu\.be\/)([a-zA-Z0-9_-]{11})/;
  var match = link.match(reg); // match[1]에 들어있음
  if (match && match[1].length == 11){
    var url = '/api/' + match[1] + '?count=' + count;
  }
  else{
    window.alert("동영상을 찾을 수 없습니다.");
  }
  if(count < 1){
    window.alert("최소 추첨 인원 수는 1명 이상이어야 합니다.");
  }
  else if(count > 10){
    window.alert("최대 추첨 인원 수는 10명 아래입니다.");
  }
  else{
    commentListElement.innerHTML = '<img src="loading.gif"/>';
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