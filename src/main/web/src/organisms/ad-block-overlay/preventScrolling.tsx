import { useEffect } from "react";

export const usePreventScrolling = (adBlockDetected: boolean) => {
  useEffect(() => {
    if (adBlockDetected) {
      document.documentElement.style.overflow = 'hidden';
    } else {
      document.documentElement.style.overflow = 'auto';
    }
  }, [adBlockDetected]);
};