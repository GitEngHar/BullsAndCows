### オブジェクトの宣言

#### @AllArgsConstructor

全フィールドへ値をセットするコンストラクタ
自動的にコンストラクタ(クラス呼び出し時に自動実行される)

◇ 使用例
```java
import lombok.AllArgsConstructor;
@AllArgsConstrunctor //以降にアノテーションを付与
public class Person {
  private String name;
  private int age;
  private String address;
}
```

アノテーションにより暗黙的に自動作成されるコンストラクタ
```java
public Person(String name, int age, String address){
  this.name = name;
  this.age = age;
  this.address = address;
}
```

オブジェクトにわざわざ代入の記述を不要にし、コードの可読性を向上させることができる


#### @Getter

フィールドに対するgetterメソッド
ゲッターメソッドを省略できるアノテーション

```java
import lombok.Getter;
public class Person{
  @Getter
  String name;

  @Getter
  String address;
}
```

暗黙的に以下のゲッターメソッドが生成される

```java
public String getName(){
  return name:
}
public String getAddress(){
  return address;
}

わざわざ宣言した値を取得する記述が不要となり可読性が向上する
```

### セッション

セッションはユーザーごとの処理を明確に分けるときに必要であるといえる   
ユーザーA が 購入した商品 ab は ユーザーAのカートに保持される必要があり、勿論ユーザーBと買い物かごが混同してはいけない  
その分別をセッションによってつけることができるわけである  

#### セッションについて理解せよ

##### 2種類のデータ型

- 基本データ型
  - byte,short,int,long,float,double,char,boolean
  - 変数にそのまま値が代入される形で利用される型

- 参照型 (ポインタ的な奴)
  - クラス(class),インターフェース(interface),配列(array)
  - `new Integer(100)` 等の `new` を利用した宣言も参照型
  - 値を代入した変数には値が格納されているピープエリアの位置が代入されている

コードを交えて説明

```java
Integer num;
num = new Integer(100)
```

上記だと参照型の変数を宣言した後、ピープメモリに格納されている数値100の場所を代入している  

以下のように参照型の値を合わせることも可能  

```java
Interger num100 = new Interger(100);
Interger numb100 = num100;
Interger num100 = new Interger(200);
```

結果は numb100の値が100 , num100の値が200となる。

#### セッション◇ 使用例の流れ

セッションはHttpSessionクラス型のオブジェクトとなり、参照型。

1. @Autowriedを用いて変数 `session` がWebアクセスと同時にセッション領域の展開
2. `session.setAttribute("number",number)` でsessionで参照している先に "number":値 を格納する
3. セッションを終了する。
4. 再度別のセッションを開始し、
#### HttpSessionクラス　フィールドインジェクションでの初期化

通常、HttpSessionオブジェクト(セッション)は、`HttpServletRequest`オブジェクトから `getSession()` で取得するが `@Autowritedアノテーション` を使用することでコントローラクラス起動時、自動で処理が行われ session に設定される  
つまりは **自分でセッションのインスタンスをセットする必要がない**

現在は **コンストラクタインジェクション** が主流である

@SessionAttribute や @Scope アノテーションを使う方法もある

#### session 機能
- session.invalidate()
  - セッション内の情報ををクリアにする
- session.setAttribute("aaa",aaa);
  - セッションに値を格納
- (Integer)session.getAttribute("aaa")
  - セッションから値を取得 (この場合aaaの値を取得しInt型へキャストする)
### Tips

#### 乱数の生成

```java
Random rnd = new Random();
int answer = rnd.nextInt(100) + 1; 
```

nextInt()は0~引数を超えない範囲でint型の乱数を返す

#### Autoboxing

javaで言われる自動型変換

#### List(可変配列)

- 宣言
  - integer型で作成したいとき
    - List<integer> number = new ArrayList<integer>(12)
  - 独自のインターフェースで作成したいとき
    - List<myinterface>hello = new ArrayList<myinterface>()
- 利用
  - 値を追加したいとき
    - number.add(12); //12の値を追加
  - 値を取得したいとき
    - System.out.println(number.get(0)); //0番目の値を参照
  - 値を削除したいとき
    - number.subList(0,1).clear(); //0番目の値を削除(範囲削除も可能)
