import { useCallback } from "react";
import axios from "axios";

function useAxios() {
  const sendRequest = useCallback(async (reqConfig, applyData) => {
    try {
      const response = await axios({
        method: reqConfig.method ? reqConfig.method : "GET",
        url: reqConfig.url,
        data: reqConfig.data,
        headers: {
          "Content-type": "application/json",
        },
      });
      const data = await response;
      applyData(data);
    } catch (error) {}
  }, []);

  return { sendRequest };
}

export default useAxios;
