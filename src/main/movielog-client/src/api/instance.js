import axios from "axios";

const baseURL = "https://ae2b5f2e-d04e-4000-9957-16244cd553f8.mock.pstmn.io";
const api = axios.create({ baseURL });

api.interceptors.request.use(
  (config) => {
    const token =
      "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJoaWhpQGdtYWlsLmNvbSIsInJvbGVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE2NTIzMzE1NzcsImV4cCI6MTY1MjMzMzM3N30.Khci76q7WZ1WWXeQoWmIolbve9cf2QKTRTOm0pBiRWMe";
    const headers = {
      ...config.headers,
      authorization: `Bearer ${token}`,
      "Content-type": "application/json",
    };
    config.headers = headers;

    return config;
  },
  (err) => console.error(err)
);

export default api;
