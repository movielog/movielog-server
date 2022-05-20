import api from "./instance";

function postLogin(userId, password, callback) {
  api
    .post("/login", {
      userId,
      password,
    })
    .then((res) => {
      if (res.status === 400) {
        callback({ error: "아이디 또는 비밀번호를 다시 확인해주세요." });
        return;
      } else {
        const { data } = res;
        const { token, nickname } = data;
        window.localStorage.setItem("token", token);
        window.localStorage.setItem("nickname", nickname);
        callback({ nickname });
      }
    });
}

export { postLogin };
