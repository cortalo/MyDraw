"use client"; // if using App Router

import { ReactSketchCanvas, ReactSketchCanvasRef } from "react-sketch-canvas";
import { useRef } from "react";

export default function DrawPage() {
  const canvasRef = useRef<ReactSketchCanvasRef>(null);

  return (
    <div
      style={{
        width: "100vw",
        height: "100vh",
        display: "flex",
        flexDirection: "column",
      }}
    >
      {/* Toolbar */}
      <div
        style={{
          padding: "10px",
          background: "#f0f0f0",
          display: "flex",
          gap: "10px",
        }}
      >
        <button onClick={() => canvasRef.current?.undo()}>Undo</button>
        <button onClick={() => canvasRef.current?.redo()}>Redo</button>
        <button onClick={() => canvasRef.current?.clearCanvas()}>Clear</button>
        <button onClick={() => canvasRef.current?.eraseMode(true)}>
          Eraser
        </button>
        <button onClick={() => canvasRef.current?.eraseMode(false)}>Pen</button>

        <input
          type="color"
          onChange={(e) =>
            canvasRef.current?.exportPaths().then(() => {
              canvasRef.current?.resetCanvas();
            })
          }
        />
      </div>

      {/* Canvas */}
      <div style={{ flex: 1, position: "relative" }}>
        <ReactSketchCanvas
          ref={canvasRef}
          style={{ border: "1px solid #ccc" }}
          width="100%"
          height="100%"
          strokeWidth={4}
          strokeColor="black"
          canvasColor="white"
        />
      </div>
    </div>
  );
}
