# FitTrack - Camada de Domínio

Este módulo implementa a **camada de domínio** do sistema **FitTrack**, responsável por modelar as entidades centrais, enums e regras de negócio do projeto.  

## 📌 Objetivo
A camada de domínio abstrai o núcleo do sistema, representando:
- **Usuário** e seus dados pessoais.
- **Atividades físicas** realizadas.
- **Metas recorrentes** definidas pelo usuário.
- **Configurações de notificação**.
- **Enums** de apoio para garantir consistência (intensidade, tipo de meta, recorrência, etc.).

Essa camada não depende de frameworks externos (como banco de dados ou APIs), sendo composta apenas por classes, enums e regras de negócio.