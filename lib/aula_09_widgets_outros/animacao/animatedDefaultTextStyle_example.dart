import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {

  MyApp({ Key? key}) : super(key: key);

  @override
  _MyApp createState() => _MyApp();

}

class _MyApp extends State {
  var _texto = "Texto padrão";
  var _color = Colors.red;
  var _fontSize = 20.0;
  bool _return = false;

  @override
  Widget build(BuildContext context){
    return MaterialApp(home: Scaffold(
          body: Center(child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              AnimatedDefaultTextStyle(
                duration: Duration(seconds: 3),
                style: TextStyle(
                  color: _color, fontSize: _fontSize
                ),
                child: Text(_texto)
              ),
              ElevatedButton(
                child: Text("Enhance!"),
                onPressed: () {
                  _color = Colors.blue;
                  _return == false ? _fontSize = 40.0 : _fontSize = 20.0;
                  _return == false ? _texto = "Texto grande" : _texto = "Texto pequeno";
                  _return = !_return;
                  setState(() {});
                },
              ),
            ],
          )),
    ));
  }
}