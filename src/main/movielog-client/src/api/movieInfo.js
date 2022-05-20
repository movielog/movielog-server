import api from "./instance";
function getMovie(movie_image, movie_title, movie_date, director, callback) {
  api
    .get("/movie", {
      movie_image,
      movie_title,
      movie_date,
      director,
    })
    .then((res) => {
      if (res.status === 400) {
        callback({ error: "아이디 또는 비밀번호를 다시 확인해주세요." });
        return;
      } else {
        const { data } = res;
        const { movie_image, movie_title, movie_date, director } = data;
        callback({ movie_image, movie_title, movie_date, director });
      }
    });
}

export { getMovie };
