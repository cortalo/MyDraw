"use client";

import { useEffect, useRef, useState } from "react";
import getStroke from "perfect-freehand";

export default function DrawPage() {
  const canvasRef = useRef<HTMLCanvasElement>(null);
  const [isDrawing, setIsDrawing] = useState(false);
  const [points, setPoints] = useState<number[][]>([]);
  const [dimensions, setDimensions] = useState({ width: 800, height: 600 });

  const getSvgPathFromStroke = (stroke: number[][]) => {
    if (!stroke.length) return "";
    const d = stroke.reduce(
      (acc, [x0, y0], i, arr) => {
        const [x1, y1] = arr[(i + 1) % arr.length];
        acc.push(x0, y0, (x0 + x1) / 2, (y0 + y1) / 2);
        return acc;
      },
      ["M", ...stroke[0], "Q"]
    );
    d.push("Z");
    return d.join(" ");
  };

  const handlePointerDown = (e: React.PointerEvent) => {
    setIsDrawing(true);
    setPoints([[e.clientX, e.clientY, e.pressure]]);
  };

  const handlePointerMove = (e: React.PointerEvent) => {
    if (!isDrawing) return;
    setPoints([...points, [e.clientX, e.clientY, e.pressure]]);
  };

  const handlePointerUp = () => {
    setIsDrawing(false);
  };

  useEffect(() => {
    const updateDimensions = () => {
      setDimensions({
        width: window.innerWidth,
        height: window.innerHeight,
      });
    };

    updateDimensions();
    window.addEventListener("resize", updateDimensions);
    return () => window.removeEventListener("resize", updateDimensions);
  }, []);

  useEffect(() => {
    const canvas = canvasRef.current;
    if (!canvas) return;

    const ctx = canvas.getContext("2d");
    if (!ctx) return;

    ctx.clearRect(0, 0, canvas.width, canvas.height);

    if (points.length > 0) {
      const stroke = getStroke(points, { size: 8, smoothing: 0.5 });
      const pathData = getSvgPathFromStroke(stroke);
      const path = new Path2D(pathData);
      ctx.fill(path);
    }
  }, [points]);

  return (
    <canvas
      ref={canvasRef}
      width={dimensions.width}
      height={dimensions.height}
      onPointerDown={handlePointerDown}
      onPointerMove={handlePointerMove}
      onPointerUp={handlePointerUp}
      style={{ touchAction: "none", cursor: "crosshair" }}
    />
  );
}
