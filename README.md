# Welocom To BullsAndCows WebApps🎉

ゲームは[こちら](https://bullscowsgame.net/)から

## 非エンジニア方向け

### BullsAndCowsって何?

Bulls and Cowsは、4桁の異なる数字を選んで秘密の数字とし、相手がその数字を少ない予測回数で当てる数当てゲームです  
各予測に対して、数字と位置が一致する部分を"Bull"、数字は一致するが位置が異なる部分を"Cow"としてヒントを与え、相手はこれを元に正確な数字を推理し続けます  
戦略的な思考と論理的な推理が求められる楽しいゲームです

### 最小回答数を目指そう！

私の知っている中で最小回答で達成した人は 4回です (7~8が平均と感じています) !!  
一手一手、数字の謎を解き明かし、最短でゲームを制覇しよう！

### Game画面

|プレイ画面|回答を見る|クリア画面|
|-|-|-|
|<img src="https://github.com/GitEngHar/BullsAndCows/assets/119464648/c0bd5195-926b-43b7-a63c-fef3fcc2e987" width="80%">|<img src="https://github.com/GitEngHar/BullsAndCows/assets/119464648/133eabb4-2566-49e5-a25a-fb6693f26ed0" width="80%" />|<img src="https://github.com/GitEngHar/BullsAndCows/assets/119464648/65273d36-d77c-4e1c-a06f-5c293c6296f7" width="80%" />

## エンジニア向け

### アプリ開発

フレームワークを使用してみたいのと、Javaの経験を積みたい理由から開発は以下で実施しました  

- FrameWork
  - SpringBoot3
- バックエンド(Controller等)
  - Java
- フロント
  - Html/css/javascript

### インフラ開発

費用、運用コストを最重要としております。そのためウェブアプリは深夜帯(23:59~5:59)は稼働しません  
大規模なアクセスは想定していないかつ、システムの稼働の安定性を重要視することもないのでシングル構成になっています  

#### 全体構成図
![BullsAndCows](https://github.com/GitEngHar/BullsAndCows/assets/119464648/81eb9179-e3f3-45d0-928d-855d36d88a8b)


### 今後実装したい機能

今回は SAA で学んだことを生かし、フレームワークの学習のOUTPUTも兼ねたOUTPUTとして制作してみました  
結果、ウェブアプリをHttpsとして公開しサーバの運用を節約できるインフラストラクチャを迅速に構築できるようになりました (性能・信頼性の担保を必要とするシステムの構築もやっていきたいです) 🎉  
今後以下の要素を追加して改修していきたいです  

- 開発の効率化
  - CICDの工程自動化による迅速でセキュアなデプロイの実現
- ゲーム機能の追加
  - ランキング機能
    - RDSによるユーザー別スコア管理
  - ユーザーサインイン機能  
    - Cognitoによるユーザー情報管理。(GoogleとのSSO)
