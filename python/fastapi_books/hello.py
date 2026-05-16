from fastapi import FastAPI, Body

app = FastAPI()
'''@app.get("/hi/{who}")
def greet_user(who):
    return f"Hello? {who}?"
'''
@app.post("/hi")
def greet_user(who:str = Body(embed=True)):
    return f"Hello? {who}?"

if __name__ == "__main__":
    import uvicorn
    uvicorn.run("hello:app", reload=True)