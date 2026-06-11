# AI Chat Agent 🤖

A CLI-based AI Chat Agent built in Java using Spring Boot and Groq LLM API.

## Features
- Multi-turn conversation with memory (context window management)
- Agentic tool use with slash commands
- System Design & LLD assistant

## Commands
| Command | Description |
|---|---|
| `/design <system>` | Generate HLD + LLD for any system |
| `/review <code>` | Review code for issues |
| `/explain <code>` | Explain code step by step |
| `/clear` | Reset conversation memory |
| `exit` | Quit |

## Tech Stack
- Java 17, Spring Boot
- Groq API (LLaMA 3.1)
- Multi-turn LLM conversation management

## Setup
1. Get free API key at console.groq.com
2. Set environment variable:
   \`\`\`bash
   export GROQ_API_KEY=your_key_here
   \`\`\`
3. Run `Main.java`

## Key Concepts Demonstrated
- LLM API integration
- Context window & conversation history management
- Agentic workflows with tool use
- System prompt engineering