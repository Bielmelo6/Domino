import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context){
    return MaterialApp(home: Scaffold(body: Home()));
  }
}

class Home extends StatelessWidget {
  @override
  Widget build(BuildContext context){
    Size size = MediaQuery.of(context).size;
    return Scaffold(
        body: Column(
            children: [
              Container(
           height: 110,
        ),
              Center(
                child: Dismissible(
                    key: GlobalKey(),
                    onDismissed: (direction) { print("Goodbye!"); },
                    child: Container(
                      color: Colors.yellow, width: size.width * 0.8, height: size.height * 0.8,
                      child: Text("Swipe me")
                    )
                ),
              )
            ]
        )
    );
  }
}
