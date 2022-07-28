const { createProxyMiddleware } = require("http-proxy-middleware");

module.exports = (app) => {
  app.use(
    // Server api path
    "/api",
    createProxyMiddleware({
      // Server 주소
      target: "http://localhost:8080",
      changeOrigin: true,
    })
  );
};
